package kz.ferius_057.ruminebot.command.api;

import api.longpoll.bots.model.objects.basic.Message;

/**
 * @author whilein
 */
public interface CommandManager {

    boolean run(Message message);

    void register(Command command);

}
