package kz.ferius_057.ruminebot;

import api.longpoll.bots.exceptions.VkApiException;
import kz.ferius_057.ruminebot.data.Config;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.database.ChatRepositoryImpl;
import kz.ferius_057.ruminebot.database.Database;
import kz.ferius_057.ruminebot.longpoll.LongPollHandler;
import kz.ferius_057.ruminebot.util.AutoUpdateUser;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.io.IoBuilder;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.concurrent.*;

import static com.google.common.math.LongMath.factorial;


public final class Main {

    @Setter
    @Getter
    private static Manager manager;

    static {
        /* for logging */
        System.setErr(IoBuilder.forLogger().setLevel(Level.ERROR).buildPrintStream());
        System.setOut(IoBuilder.forLogger().setLevel(Level.INFO).buildPrintStream());
    }

    public static void main(String[] args) throws IOException, SQLException, VkApiException {
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

        // для обновления юзеров в чате
        val scheduledFuture = Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(() -> {
            System.out.println("update users in 2000000001 chat");
            AutoUpdateUser.updateChatUsers(2000000001, chatRepository, manager.vk());
        }, 1, 1, TimeUnit.HOURS);

        // при завершении всё выключить
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            database.close();
            longPollHandler.stopPolling();
            scheduledFuture.cancel(true);
        }));

        System.out.println("Запуск LongPoll...");
        // run
        longPollHandler.startPolling();
    }
}