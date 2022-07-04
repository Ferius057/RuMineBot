package kz.ferius_057.ruminebot.command.role;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;

import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.object.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Admin extends AbstractCommand {

    public Admin(Manager Manager) {
        super(Manager, "admin", "админ");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        if (cache.getSenderUserChat().getRole() < 1) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ [id" + message.getFromId() + "|" + cache.getSenderUserChat().getNickname() + "], у вас недостаточно прав для данной команды.")
                    .execute();
        } else {
            Message replyMessage = replyMessages.get(0);

            User replySender = cache.getReplySenders().get(0);
            UserChat replySenderUserChat = cache.getReplySendersUserChat().get(0);

            if (replySenderUserChat == null) {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❌ [id" + replySender.getUserId() + "|" + replySender.getFirstName()[0] + " " + replySender.getLastName()[0] + "] отсутствует в этой беседе.")
                        .execute();
                return;
            }

            if (replySenderUserChat.getRole() == 0) {
                chatRepository.updateRole(replyMessage.getFromId(), peerId, 1);
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("✅ [id" + replyMessage.getFromId() + "|" + replySender.getFirstName()[0] + " " + replySender.getLastName()[0] + "] теперь админ.")
                        .execute();
            } else {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❗ [id" + replyMessage.getFromId() + "|" + replySender.getFirstName()[0] + " " + replySender.getLastName()[0] + "] уже имеет роль админа.")
                        .execute();
            }

        }
    }
}