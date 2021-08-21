package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkData;


public class Command {
    VkApiClient vk = new VkData().getVk();
    GroupActor actor = new VkData().getActor();
    static Message message;

    public static void command(final Message message) throws ClientException, ApiException {
        Command.message = message;
        switch (message.getText()) {
            case "!активация":
            case "!register":
            case "!reg": new RegisteringConversation().register();
        }
    }
}