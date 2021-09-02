package kz.ferius_057.ruminebot.event.api;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.MessageActionStatus;
import kz.ferius_057.ruminebot.VkApi;

public abstract class AbstractEvent implements Event {

    protected final VkApi vkApi;
    protected final MessageActionStatus eventName;
    protected final GroupActor actor;
    protected final VkApiClient vk;

    protected AbstractEvent(final VkApi vkApi, final MessageActionStatus eventName) {
        this.vkApi = vkApi;
        this.eventName = eventName;
        this.vk = vkApi.getClient();
        this.actor = vkApi.getActor();
    }

    @Override
    public MessageActionStatus getEventName() {
        return eventName;
    }
}