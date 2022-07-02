package kz.ferius_057.ruminebot.command.api;

import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.data.LocalData;

/**
 * @author whilein
 */
public interface CommandManager {
    boolean run(Message message);
    void register(Command command);

}
