package kz.ferius_057.ruminebot.event.api;

import api.longpoll.bots.model.objects.basic.Message;

/**
 * @author Charles_Grozny
 */
public interface Event {

    void run(Message message, Message.Action action);
}