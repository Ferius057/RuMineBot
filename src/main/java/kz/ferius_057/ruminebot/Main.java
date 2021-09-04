package kz.ferius_057.ruminebot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import kz.ferius_057.ruminebot.command.api.CommandManager;
import kz.ferius_057.ruminebot.command.api.SimpleCommandManager;
import kz.ferius_057.ruminebot.command.api.tool.UptimeTool;
import kz.ferius_057.ruminebot.data.Config;
import kz.ferius_057.ruminebot.database.ChatDao;
import kz.ferius_057.ruminebot.database.Database;
import kz.ferius_057.ruminebot.event.api.EventManager;
import kz.ferius_057.ruminebot.event.api.SimpleEventManager;
import kz.ferius_057.ruminebot.longpoll.CallbackApiLongPollHandler;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

public final class Main {
    public static void main(String[] args) throws IOException, ClientException, ApiException, SQLException {
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
        ChatDao chatDao = ChatDao.create(database);
        chatDao.createTables();

        Set<Integer> peerIds = chatDao.getChats();
        Set<Integer> users = chatDao.getUsers();

        VkApiClient client = new VkApiClient(new HttpTransportClient());
        GroupActor actor = new GroupActor(config.getGroupId(), config.getToken());

        VkApi vkApi = new VkApiImpl(chatDao, peerIds, users, client, actor);

        CommandManager commandManager = SimpleCommandManager.create(vkApi);
        EventManager eventManager = SimpleEventManager.create(vkApi);

        CallbackApiLongPollHandler handler = new CallbackApiLongPollHandler(client, actor, commandManager, eventManager);
        handler.run();

        Runtime.getRuntime().addShutdownHook(new Thread(database::close));
    }

    private static final class VkApiImpl implements VkApi {
        private final ChatDao chatDao;
        private final Set<Integer> peerIds;
        private final Set<Integer> users;
        private final VkApiClient client;
        private final GroupActor actor;

        private VkApiImpl(final ChatDao chatDao, final Set<Integer> peerIds, final Set<Integer> users, final VkApiClient client, final GroupActor actor) {
            this.chatDao = chatDao;
            this.peerIds = peerIds;
            this.users = users;
            this.client = client;
            this.actor = actor;
        }

        @Override
        public ChatDao getChatDao() {
            return chatDao;
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