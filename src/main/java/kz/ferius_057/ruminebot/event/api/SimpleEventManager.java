package kz.ferius_057.ruminebot.event.api;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.event.ChatInvite;
import kz.ferius_057.ruminebot.event.ChatInviteByLink;
import kz.ferius_057.ruminebot.event.ChatKick;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    public final class SimpleEventManager implements EventManager {
        Map<String, Event> eventMap;

    private SimpleEventManager(final Map<String, Event> eventMap) {
        this.eventMap = eventMap;
    }

    public static EventManager create(final Manager manager) {
        EventManager commandManager = new SimpleEventManager(new HashMap<>());
        commandManager.register(new ChatInvite(manager));
        commandManager.register(new ChatKick(manager));
        commandManager.register(new ChatInviteByLink(manager));

        return commandManager;
    }

    @Override
    public boolean run(final Message message, final Message.Action action) {
        Event event = eventMap.get(action.getType());
        if (event == null) return false;

        try {
            event.run(message, action);
        } catch (VkApiException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void register(final Event event) {
        eventMap.put(event.getEventName().getValue(), event);
    }
}