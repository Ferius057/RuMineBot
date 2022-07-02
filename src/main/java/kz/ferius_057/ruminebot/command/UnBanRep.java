package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class UnBanRep extends AbstractCommand {

    public UnBanRep(Manager Manager) {
        super(Manager, "unbanrep", "repunban", "разбанреп", "репразбан");
    }

    @Override
    public void run(User userSender, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        UserChat sender = chatRepository.getUserFromChat(message.getFromId(), peerId);
        if (sender.getRole() < 1) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ [id" + peerId + "|" + sender.getNickname() + "], у вас недостаточно прав для данной команды.")
                    .execute();
        } else {
            Message replyMessage = replyMessages.get(0);

            User user = User.get(manager, replyMessage.getFromId());

            String userName = user.getFirstName()[2] + " " + user.getLastName()[2];

            UserChat userInPeerId = chatRepository.getUserFromChat(replyMessage.getFromId(), peerId);

            if (userInPeerId == null) {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❌ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] отсутствует в этой беседе.")
                        .execute();
                return;
            }

            if (userInPeerId.isBanrep()) {
                chatRepository.updateBanReputation(replyMessage.getFromId(), peerId, false);
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("✅ Снял бан репутации [id" + replyMessage.getFromId() + "|" + userName + "].").execute();
            } else {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❗ У [id" + replyMessage.getFromId() + "|" + user.getFirstName()[1] + " " + user.getLastName()[1] + "] нету бана репутации.")
                        .execute();
            }
        }
    }
}