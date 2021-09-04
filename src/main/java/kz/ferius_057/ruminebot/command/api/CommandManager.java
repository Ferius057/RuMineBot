package kz.ferius_057.ruminebot.command.api;

import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.data.LocalData;

/**
 * @author whilein
 */
public interface CommandManager {

    boolean run(LocalData localData, Message message);
    void register(Command command);

}
