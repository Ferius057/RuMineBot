package kz.ferius_057.ruminebot;

import kz.ferius_057.ruminebot.data.Config;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.database.ChatRepositoryImpl;
import kz.ferius_057.ruminebot.database.Database;
import kz.ferius_057.ruminebot.longpoll.LongPollHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.io.IoBuilder;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

public final class Main {

    @Setter
    @Getter
    private static Manager manager;

    static {
        /* for logging */
        System.setErr(IoBuilder.forLogger().setLevel(Level.ERROR).buildPrintStream());
        System.setOut(IoBuilder.forLogger().setLevel(Level.INFO).buildPrintStream());
    }

    public static void main(String[] args) throws IOException, SQLException {
        val localData = new LocalData();
        localData.setTimeStartMs(System.currentTimeMillis()); // установка времени запуска

        val config = Config.load(Paths.get("config.properties"));
        if (config.getFileNameDataBase().isEmpty()) {
            System.err.println("Установите название файла базы данных.");
            return;
        }

        if (config.getToken().isEmpty()) {
            System.err.println("Установите токен.");
            return;
        }

        val database = Database.create(config.getFileNameDataBase());

        val chatRepository = new ChatRepositoryImpl(database);
        val longPollHandler = new LongPollHandler(
                config.getToken(),
                new ManagerImpl(chatRepository, chatRepository.getChats(), chatRepository.getUsers(), null, localData)
        );

        // при завершении всё выключить
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            database.close();
            longPollHandler.stopPolling();
        }));


        try {
            System.out.println("Запуск LongPoll...");
            // run
            longPollHandler.startPolling();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}