package kz.ferius_057.ruminebot.longpoll;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkData;
import kz.ferius_057.ruminebot.command.Command;
import kz.ferius_057.ruminebot.command.Kick;
import kz.ferius_057.ruminebot.command.Manager;
import kz.ferius_057.ruminebot.command.RegisteringConversation;
import kz.ferius_057.ruminebot.database.Data;

public class CallbackApiLongPollHandler extends CallbackApiLongPoll {
    private final Manager manager = new Manager();
    private final VkApiClient vkApiClient = new VkData().getVk();
    private final GroupActor groupActor = new VkData().getActor();

    public CallbackApiLongPollHandler(VkApiClient client, GroupActor actor) {
        super(client, actor);
        registerCommands();
    }

    @Override
    public void messageNew(Integer groupId, final Message message) {
        System.out.println("\nNew message: " + message.getText());
        manager.execute(vkApiClient, groupActor, message);
    }

    /*
    * Регистрация комманд
    */
    private void registerCommands() {
        manager.register(new RegisteringConversation());
        manager.register(new Kick());
    }
}