package kz.ferius_057.ruminebot.command.role;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.tool.User;
import kz.ferius_057.ruminebot.command.api.tool.UserInPeerId;
import kz.ferius_057.ruminebot.database.ChatDao;

/**
 * @author Charles_Grozny
 */
public class Default extends AbstractCommand {

    public Default(VkApi vkApi) {
        super(vkApi, "default", "участник");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        int peerId = message.getPeerId();
        ForeignMessage replyMessage = getForeignMessage(message);

        if (replyMessage != null) {
            User user = User.user(vkApi, replyMessage.getFromId().toString());

            UserInPeerId userInPeerId = chatDao.getUserInPeerId(peerId + "_" + replyMessage.getFromId());

            if (userInPeerId.getRole() == 0) {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❗ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] уже имеет роль участника.").execute();
            } else {
                chatDao.updateRole(peerId + "_" + replyMessage.getFromId(), 0);
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("✅ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] теперь участник.").execute();
            }
        }
    }
}