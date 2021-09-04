package kz.ferius_057.ruminebot.longpoll;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.command.api.CommandManager;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.event.api.EventManager;

public class CallbackApiLongPollHandler extends CallbackApiLongPoll {
    LocalData localData = new LocalData();

    private final CommandManager commandManager;
    private final EventManager eventManager;

    public CallbackApiLongPollHandler(final VkApiClient client, final GroupActor actor,
                                      final CommandManager commandManager,
                                      final EventManager eventManager) {
        super(client, actor);

        this.commandManager = commandManager;
        this.eventManager = eventManager;
    }

    @Override
    public void messageNew(Integer groupId, final Message message) {
        System.out.println("\nNew message: " + message.getText());
        System.out.println(message);
        if (!commandManager.run(localData, message)) {
            if (message.getAction() != null) {
                eventManager.run(message, message.getAction());
            }
        }
    }
}