package kz.ferius_057.ruminebot.event.api;

import api.longpoll.bots.model.objects.basic.Message;

public interface EventManager {
    boolean run(Message message, Message.Action action);

    void register(Event command);
}