package kz.ferius_057.ruminebot.command.reputation;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.Permission;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.object.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
@Permission(value = 1)
public class BanRep extends AbstractCommand {

    public BanRep(Manager Manager) {
        super(Manager, "banrep", "repban", "банреп", "репбан");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {

        // TODO: 04.07.2022 | сделать что бы всем reply юзерам давался банреп

        int peerId = message.getPeerId();


        Message replyMessage = replyMessages.get(0);

        User replySender = cache.getReplySenders().get(0);
        UserChat replySenderUserChat = cache.getReplySendersUserChat().get(0);

        if (replySenderUserChat == null || !replySenderUserChat.isExist()) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ " + replySender.getFullName().get(0).getPush() + " отсутствует в этой беседе.")
                    .execute();
            return;
        }

        if (!replySenderUserChat.isBanrep()) {
            chatRepository.updateBanReputation(replyMessage.getFromId(), peerId, true);
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("✅ Выдал бан репутации " + replySender.getFullName().get(2).getPush() + ".")
                    .execute();
        } else {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ У " + replySender.getFullName().get(1).getPush() + " уже имеется бан репутации.")
                    .execute();
        }
    }
}