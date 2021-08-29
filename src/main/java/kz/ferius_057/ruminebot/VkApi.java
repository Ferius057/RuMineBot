package kz.ferius_057.ruminebot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import kz.ferius_057.ruminebot.database.ChatDao;

import java.util.List;
import java.util.Set;

/**
 * @author whilein
 */
public interface VkApi {

    ChatDao getChatDao();

    Set<Integer> getPeerIds();
    Set<Integer> getUsers();

    VkApiClient getClient();
    GroupActor getActor();
}
