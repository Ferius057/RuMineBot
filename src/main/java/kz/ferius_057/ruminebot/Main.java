package kz.ferius_057.ruminebot;

import kz.ferius_057.ruminebot.data.Config;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.database.ChatRepository;
import kz.ferius_057.ruminebot.database.ChatRepositoryImpl;
import kz.ferius_057.ruminebot.database.Database;
import kz.ferius_057.ruminebot.database.tool.UptimeTool;
import kz.ferius_057.ruminebot.longpoll.LongPollHandler;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

public final class Main {
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        long timeStartMs = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        UptimeTool uptime = new UptimeTool();
        uptime.setTimeStart(simpleDateFormat.format(new Date(timeStartMs)));
        uptime.setTimeStartMs(timeStartMs);

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

        Manager manager = new ManagerImpl(chatRepository, chats, users, null, new LocalData());

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