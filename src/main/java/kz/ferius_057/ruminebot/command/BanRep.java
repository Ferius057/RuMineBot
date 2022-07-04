package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.object.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class BanRep extends AbstractCommand {

    public BanRep(Manager Manager) {
        super(Manager, "banrep", "repban", "банреп", "репбан");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {

        // TODO: 04.07.2022 | сделать что бы всем reply юзерам давался банреп

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

            String userName = replySender.getFirstName()[2] + " " + replySender.getLastName()[2];

            if (replySenderUserChat == null || !replySenderUserChat.isExist()) {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❗ [id" + replyMessage.getFromId() + "|" + replySender.getFirstName()[0] + " " + replySender.getLastName()[0] + "] отсутствует в этой беседе.")
                        .execute();
                return;
            }

            if (!replySenderUserChat.isBanrep()) {
                chatRepository.updateBanReputation(replyMessage.getFromId(), peerId, true);
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("✅ Выдал бан репутации [id" + replyMessage.getFromId() + "|" + userName + "].")
                        .execute();
            } else {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❗ У [id" + replyMessage.getFromId() + "|" + replySender.getFirstName()[1] + " " + replySender.getLastName()[1] + "] уже имеется бан репутации.")
                        .execute();
            }
        }
    }

}