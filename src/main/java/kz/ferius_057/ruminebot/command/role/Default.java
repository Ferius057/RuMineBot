package kz.ferius_057.ruminebot.command.role;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.Permission;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.object.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */

@Permission(value = 1)
public class Default extends AbstractCommand {

    public Default(Manager Manager) {
        super(Manager, "default", "участник");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        Message replyMessage = replyMessages.get(0);

        User replySender = cache.getReplySenders().get(0);
        UserChat replySenderUserChat = cache.getReplySendersUserChat().get(0);

        if (replySenderUserChat == null) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❌ " + replySender.getFullName().get(0).getPush() + " отсутствует в этой беседе.")
                    .execute();
            return;
        }

        if (replySenderUserChat.getRole() == 0) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ " + replySender.getFullName().get(0).getPush() + " уже имеет роль участника.")
                    .execute();
        } else {
            chatRepository.updateRole(replyMessage.getFromId(), peerId, 0);
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("✅ " + replySender.getFullName().get(0).getPush() + " теперь участник.")
                    .execute();
        }
    }
}