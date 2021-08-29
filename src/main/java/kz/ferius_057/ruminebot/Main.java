package kz.ferius_057.ruminebot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import kz.ferius_057.ruminebot.command.CommandManager;
import kz.ferius_057.ruminebot.command.SimpleCommandManager;
import kz.ferius_057.ruminebot.data.Config;
import kz.ferius_057.ruminebot.database.ChatDao;
import kz.ferius_057.ruminebot.database.Database;
import kz.ferius_057.ruminebot.longpoll.CallbackApiLongPollHandler;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Main {
    public static void main(String[] args) throws IOException, ClientException, ApiException {
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

        VkApiClient client = new VkApiClient(new HttpTransportClient());
        GroupActor actor = new GroupActor(config.getGroupId(), config.getToken());

        VkApi vkApi = new VkApiImpl(chatDao, peerIds, client, actor);

        CommandManager commandManager = SimpleCommandManager.create(vkApi);

        CallbackApiLongPollHandler handler = new CallbackApiLongPollHandler(client, actor, commandManager);
        handler.run();
    }

    private static final class VkApiImpl implements VkApi {
        private final ChatDao chatDao;
        private final Set<Integer> peerIds;
        private final VkApiClient client;
        private final GroupActor actor;

        private VkApiImpl(final ChatDao chatDao, final Set<Integer> peerIds, final VkApiClient client, final GroupActor actor) {
            this.chatDao = chatDao;
            this.peerIds = peerIds;
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
        public VkApiClient getClient() {
            return client;
        }

        @Override
        public GroupActor getActor() {
            return actor;
        }
    }
}