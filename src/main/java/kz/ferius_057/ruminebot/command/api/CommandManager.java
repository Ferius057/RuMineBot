package kz.ferius_057.ruminebot.command.api;

import com.vk.api.sdk.objects.messages.Message;

/**
 * @author whilein
 */
public interface CommandManager {

    boolean run(Message message);
    void register(Command command);

}
