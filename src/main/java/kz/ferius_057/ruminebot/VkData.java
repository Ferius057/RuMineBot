package kz.ferius_057.ruminebot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VkData {
    private static final HttpTransportClient httpClient = HttpTransportClient.getInstance();
    private static final VkApiClient vk = new VkApiClient(httpClient);
    private static GroupActor actor;

    public VkApiClient getVk() {
        return vk;
    }

    public GroupActor getActor() {
        return actor;
    }

    public void setActor(GroupActor actor) {
        VkData.actor = actor;
    }
}