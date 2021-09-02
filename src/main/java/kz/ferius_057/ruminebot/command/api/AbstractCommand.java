package kz.ferius_057.ruminebot.command.api;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.GetNameCase;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.tool.User;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractCommand implements Command {
    protected final String name;
    protected final String[] aliases;
    protected final GroupActor actor;
    protected final VkApiClient vk;
    protected final VkApi vkApi;

    protected AbstractCommand(final VkApi vkApi, final String name, final String... aliases) {
        this.vkApi = vkApi;
        this.name = name;
        this.vk = vkApi.getClient();
        this.actor = vkApi.getActor();
        this.aliases = aliases;
    }

    @Override
    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    protected final ForeignMessage getForeignMessage(Message message) {
        if (message.getFwdMessages().size() != 0 || message.getReplyMessage() != null) {
            ForeignMessage replyMessage = message.getReplyMessage();
            if (replyMessage == null) return message.getFwdMessages().get(0);

            return replyMessage;
        } else {
            return null;
        }
    }
}