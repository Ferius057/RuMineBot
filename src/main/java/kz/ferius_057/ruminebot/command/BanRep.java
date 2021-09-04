package kz.ferius_057.ruminebot.command;

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
public class BanRep extends AbstractCommand {

    public BanRep(VkApi vkApi) {
        super(vkApi,"banrep", "repban","банреп","репбан");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        ForeignMessage replyMessage = getForeignMessage(message);

        int peerId = message.getPeerId();

        if (replyMessage != null) {
            String userDB = peerId + "_" + replyMessage.getFromId();

            User user = User.user(vkApi, replyMessage.getFromId().toString());
            String userName = user.getFirstName()[2] + " " + user.getLastName()[2];

            UserInPeerId userInPeerId = chatDao.getUserInPeerId(peerId + "_" + replyMessage.getFromId());

            if (userInPeerId == null) {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❗ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] отсутствует в этой беседе.").execute();
                return;
            }

            if (!userInPeerId.isBanrep()) {
                chatDao.updateBanReputation(userDB, true);
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("✅ Выдал бан репутации [id" + replyMessage.getFromId() + "|" + userName + "].").execute();
            } else {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❗ У [id" + replyMessage.getFromId() + "|" + userName + "] уже имеется бан репутации.").execute();
            }
        }
    }
}