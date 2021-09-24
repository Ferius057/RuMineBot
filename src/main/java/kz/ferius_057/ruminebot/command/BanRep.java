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
public class BanRep extends AbstractCommand {

    public BanRep(VkApi vkApi) {
        super(vkApi,"banrep", "repban","банреп","репбан");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        int peerId = message.getPeerId();

        UserChat sender = chatRepository.getUserFromChat(message.getFromId(), peerId);

        if (sender.getRole() < 1) {
            vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                    .message("❗ [id" + message.getFromId() + "|" + sender.getNickname() + "], у вас недостаточно прав для данной команды.").execute();
        } else {
            ForeignMessage replyMessage = getForeignMessage(message);
            if (replyMessage != null) {

                User user = User.user(vkApi, replyMessage.getFromId().toString());
                String userName = user.getFirstName()[2] + " " + user.getLastName()[2];

                UserChat userInPeerId = chatRepository.getUserFromChat(replyMessage.getFromId(), peerId);

                if (userInPeerId == null || !userInPeerId.isExist()) {
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("❗ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] отсутствует в этой беседе.").execute();
                    return;
                }

                if (!userInPeerId.isBanrep()) {
                    chatRepository.updateBanReputation(replyMessage.getFromId(), peerId, true);
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("✅ Выдал бан репутации [id" + replyMessage.getFromId() + "|" + userName + "].").execute();
                } else {
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("❗ У [id" + replyMessage.getFromId() + "|" + userName + "] уже имеется бан репутации.").execute();
                }
            }
        }
    }
}