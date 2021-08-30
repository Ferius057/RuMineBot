package kz.ferius_057.ruminebot.event.api;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAction;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.event.ChatKickUser;
import kz.ferius_057.ruminebot.event.ChatInviteUser;

import java.util.HashMap;
import java.util.Map;

public final class SimpleEventManager implements EventManager {
    private final VkApi vkApi;
    private final Map<String, Event> eventMap;

    private SimpleEventManager(final VkApi vkApi, final Map<String, Event> eventMap) {
        this.vkApi = vkApi;
        this.eventMap = eventMap;
    }

    public static EventManager create(final VkApi vkApi) {
        EventManager commandManager = new SimpleEventManager(vkApi, new HashMap<>());
        commandManager.register(new ChatInviteUser());
        commandManager.register(new ChatKickUser());

        return commandManager;
    }

    @Override
    public boolean run(final Message message, final MessageAction action) {
        Event event = eventMap.get(action.getType().getValue());
        if (event == null) return false;

        try {
            event.run(vkApi, message, action);
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void register(final Event event) {
        eventMap.put(event.getEventName().getValue(), event);
    }
}