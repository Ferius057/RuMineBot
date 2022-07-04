package kz.ferius_057.ruminebot.command;

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
public class UnBanRep extends AbstractCommand {

    public UnBanRep(Manager Manager) {
        super(Manager, "unbanrep", "repunban", "разбанреп", "репразбан");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        if (cache.getSenderUserChat().getRole() < 1) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ [id" + peerId + "|" + cache.getSenderUserChat().getNickname() + "], у вас недостаточно прав для данной команды.")
                    .execute();
        } else {
            Message replyMessage = replyMessages.get(0);

            User replySender = cache.getReplySenders().get(0);
            UserChat replySenderUserChat = cache.getReplySendersUserChat().get(0);

            String userName = replySender.getFirstName()[2] + " " + replySender.getLastName()[2];

            if (replySenderUserChat == null) {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❌ [id" + replyMessage.getFromId() + "|" + replySender.getFirstName()[0] + " " + replySender.getLastName()[0] + "] отсутствует в этой беседе.")
                        .execute();
                return;
            }

            if (replySenderUserChat.isBanrep()) {
                chatRepository.updateBanReputation(replyMessage.getFromId(), peerId, false);
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("✅ Снял бан репутации [id" + replyMessage.getFromId() + "|" + userName + "].").execute();
            } else {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❗ У [id" + replyMessage.getFromId() + "|" + replySender.getFirstName()[1] + " " + replySender.getLastName()[1] + "] нету бана репутации.")
                        .execute();
            }
        }
    }
}