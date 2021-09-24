package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

/**
 * @author Charles_Grozny
 */
public class UnBanRep extends AbstractCommand {

    public UnBanRep(VkApi vkApi) {
        super(vkApi,"unbanrep", "repunban","разбанреп","репразбан");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        int peerId = message.getPeerId();

        UserChat sender = chatRepository.getUserFromChat(message.getFromId(), peerId);
        if (sender.getRole() < 1) {
            vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                    .message("❗ [id" + peerId + "|" + sender.getNickname() + "], у вас недостаточно прав для данной команды.").execute();
        } else {
            ForeignMessage replyMessage = getForeignMessage(message);

            if (replyMessage != null) {
                int userId = replyMessage.getFromId();

                User user = User.user(vkApi, String.valueOf(userId));
                String userName = user.getFirstName()[2] + " " + user.getLastName()[2];

                UserChat userInPeerId = chatRepository.getUserFromChat(replyMessage.getFromId(), peerId);

                if (userInPeerId == null) {
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("❗ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] отсутствует в этой беседе.").execute();
                    return;
                }

                if (userInPeerId.isBanrep()) {
                    chatRepository.updateBanReputation(userId, peerId, false);
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("✅ Снял бан репутации [id" + replyMessage.getFromId() + "|" + userName + "].").execute();
                } else {
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("❗ У [id" + replyMessage.getFromId() + "|" + userName + "] нету бана репутации.").execute();
                }
            }
        }
    }
}