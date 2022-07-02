package kz.ferius_057.ruminebot.command.role;

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
public class Admin extends AbstractCommand {

    public Admin(Manager Manager) {
        super(Manager, "admin", "админ");
    }

    @Override
    public void run(User userSender, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        UserChat sender = chatRepository.getUserFromChat(message.getFromId(), peerId);

        if (sender.getRole() < 1) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ [id" + message.getFromId() + "|" + sender.getNickname() + "], у вас недостаточно прав для данной команды.")
                    .execute();
        } else {
            Message replyMessage = replyMessages.get(0);

            User user = User.get(manager, replyMessage.getFromId());


            UserChat userInPeerId = chatRepository.getUserFromChat(replyMessage.getFromId(), peerId);

            if (userInPeerId == null) {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❌ [id" + user.getUserId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] отсутствует в этой беседе.")
                        .execute();
                return;
            }

            if (userInPeerId.getRole() == 0) {
                chatRepository.updateRole(replyMessage.getFromId(), peerId, 1);
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("✅ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] теперь админ.")
                        .execute();
            } else {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❗ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] уже имеет роль админа.")
                        .execute();
            }

        }
    }
}