package kz.ferius_057.ruminebot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import kz.ferius_057.ruminebot.command.api.CommandManager;
import kz.ferius_057.ruminebot.command.api.SimpleCommandManager;
import kz.ferius_057.ruminebot.database.tool.UptimeTool;
import kz.ferius_057.ruminebot.data.Config;
import kz.ferius_057.ruminebot.database.ChatRepositoryImpl;
import kz.ferius_057.ruminebot.database.Database;
import kz.ferius_057.ruminebot.event.api.EventManager;
import kz.ferius_057.ruminebot.event.api.SimpleEventManager;
import kz.ferius_057.ruminebot.longpoll.CallbackApiLongPollHandler;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class Main {
    public static void main(String[] args) throws IOException, ClientException, ApiException, SQLException, InterruptedException {
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

        ChatRepositoryImpl chatRepositoryImpl = new ChatRepositoryImpl(database);
        chatRepositoryImpl.createTables();

        Set<Integer> chats = chatRepositoryImpl.getChats();
        Set<Integer> users = chatRepositoryImpl.getUsers();

        VkApiClient client = new VkApiClient(new HttpTransportClient());
        GroupActor actor = new GroupActor(config.getGroupId(), config.getToken());

        VkApi vkApi = new VkApiImpl(chatRepositoryImpl, chats, users, client, actor);

        CommandManager commandManager = SimpleCommandManager.create(vkApi);
        EventManager eventManager = SimpleEventManager.create(vkApi);

        Runtime.getRuntime().addShutdownHook(new Thread(database::close));

        // SDK кидает непонятные ошибки ежедневно, приходится юзать такой метод фикса
        boolean start = true;
        while (start) {
            try {
                run(client, actor, commandManager, eventManager);
            } catch (Exception e) {
                start = true;
                Thread.sleep(10000);
                continue;
            }
            start = false;
        }
    }
    private static void run(final VkApiClient client, GroupActor actor, CommandManager commandManager, final EventManager eventManager) throws ClientException, ApiException {
        CallbackApiLongPollHandler handler = new CallbackApiLongPollHandler(client, actor, commandManager, eventManager);
        handler.run();
    }

    private static final class VkApiImpl implements VkApi {
        private final Set<Integer> peerIds;
        private final Set<Integer> users;
        private final VkApiClient client;
        private final GroupActor actor;
        private final ChatRepositoryImpl chatRepositoryImpl;

        private VkApiImpl(final ChatRepositoryImpl chatRepositoryImpl, final Set<Integer> peerIds, final Set<Integer> users, final VkApiClient client, final GroupActor actor) {
            this.peerIds = peerIds;
            this.users = users;
            this.client = client;
            this.actor = actor;
            this.chatRepositoryImpl = chatRepositoryImpl;
        }

        @Override
        public ChatRepositoryImpl chatRepositoryImpl() {
            return chatRepositoryImpl;
        }

        @Override
        public Set<Integer> getPeerIds() {
            return peerIds;
        }

        @Override
        public Set<Integer> getUsers() {
            return users;
        }

        @Override
        public VkApiClient getClient() {
            return client;
        }

        @Override
        public GroupActor getActor() {
            return actor;
        }
    }
}