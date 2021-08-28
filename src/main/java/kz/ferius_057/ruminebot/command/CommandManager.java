package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.objects.messages.Message;

/**
 * @author whilein
 */
public interface CommandManager {

    void run(Message message);
    void register(Command command);

}
