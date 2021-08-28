package kz.ferius_057.ruminebot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

import java.util.List;

/**
 * @author whilein
 */
public interface VkApi {

    List<Integer> getPeerIds();

    VkApiClient getClient();
    GroupActor getActor();

}
