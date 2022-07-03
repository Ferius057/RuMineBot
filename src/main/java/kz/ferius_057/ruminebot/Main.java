package kz.ferius_057.ruminebot;

import kz.ferius_057.ruminebot.data.Config;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.database.ChatRepository;
import kz.ferius_057.ruminebot.database.ChatRepositoryImpl;
import kz.ferius_057.ruminebot.database.Database;
import kz.ferius_057.ruminebot.longpoll.LongPollHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.io.IoBuilder;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Set;

public final class Main {

    static {
        /* for logging */
        System.setErr(IoBuilder.forLogger().setLevel(Level.ERROR).buildPrintStream());
        System.setOut(IoBuilder.forLogger().setLevel(Level.INFO).buildPrintStream());
    }

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        LocalData localData = new LocalData();

        // установка времени запуска
        localData.setTimeStartMs(System.currentTimeMillis());


        Config config = Config.load(Paths.get("config.properties"));

        if (config.getFileNameDataBase().isEmpty()) {
            System.err.println("Установите название файла базы данных.");
            return;
        }

        if (config.getToken().isEmpty()) {
            System.err.println("Установите токен.");
            return;
        }

        Database database = Database.create(config.getFileNameDataBase());

        ChatRepository chatRepository = new ChatRepositoryImpl(database);

        Set<Integer> chats = chatRepository.getChats();
        Set<Integer> users = chatRepository.getUsers();

        Manager manager = new ManagerImpl(chatRepository, chats, users, null, localData);

        LongPollHandler longPollHandler = new LongPollHandler(config.getToken(), manager);

        // при завершении всё выключить
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            database.close();
            longPollHandler.stopPolling();
        }));

        try {
            // run
            longPollHandler.startPolling();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}