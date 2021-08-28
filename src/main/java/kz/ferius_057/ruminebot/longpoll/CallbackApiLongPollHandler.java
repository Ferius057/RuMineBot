package kz.ferius_057.ruminebot.longpoll;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.command.CommandManager;

public class CallbackApiLongPollHandler extends CallbackApiLongPoll {
    private final CommandManager commandManager;

    public CallbackApiLongPollHandler(final VkApiClient client, final GroupActor actor,
                                      final CommandManager commandManager) {
        super(client, actor);

        this.commandManager = commandManager;
    }

    @Override
    public void messageNew(Integer groupId, final Message message) {
        System.out.println("\nNew message: " + message.getText());
        commandManager.run(message);
    }

}