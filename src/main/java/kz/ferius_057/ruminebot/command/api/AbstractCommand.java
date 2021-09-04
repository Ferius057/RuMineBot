package kz.ferius_057.ruminebot.command.api;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.tool.User;
import kz.ferius_057.ruminebot.command.api.tool.UserInPeerId;
import kz.ferius_057.ruminebot.database.ChatDao;

public abstract class AbstractCommand implements Command {
    protected final VkApi vkApi;
    protected final String name;
    protected final String[] aliases;
    protected final GroupActor actor;
    protected final VkApiClient vk;
    protected final ChatDao chatDao;

    protected AbstractCommand(final VkApi vkApi, final String name, final String... aliases) {
        this.vkApi = vkApi;
        this.name = name;
        this.vk = vkApi.getClient();
        this.aliases = aliases;
        this.actor = vkApi.getActor();
        this.chatDao = vkApi.getChatDao();
    }

    @Override
    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    protected final ForeignMessage getForeignMessage(final Message message) throws ClientException, ApiException {
        if (message.getFwdMessages().size() != 0 || message.getReplyMessage() != null) {
            ForeignMessage replyMessage = message.getReplyMessage();
            if (replyMessage == null) return message.getFwdMessages().get(0);

            return replyMessage;
        } else {
            vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                    .message("❗ Сообщение должно быть ответом на другое сообщение или пересланным сообщение.").execute();
            return null;
        }
    }

    protected final UserInPeerId haveInPeerId(final User user, final UserInPeerId userInPeerId, final int peerId) throws ClientException, ApiException {
        if (userInPeerId == null) {
            vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                    .message("❗ [id" + user.getUserId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] отсутствует в этой беседе.").execute();
            return null;
        } else {
            return userInPeerId;
        }
    }
}