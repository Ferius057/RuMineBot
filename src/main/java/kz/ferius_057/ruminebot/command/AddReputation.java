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
public class AddReputation extends AbstractCommand {

    public AddReputation(Manager Manager) {
        super(Manager, "+reputation", "+rep", "+репутация", "+реп");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        Message replyMessage = replyMessages.get(0);

        int peerId = message.getPeerId();

        User replySenderUser = cache.getReplySenders().get(0);
        UserChat replySenderUserChat = cache.getReplySendersUserChat().get(0);

        if (replySenderUserChat == null || !replySenderUserChat.isExist()) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❌ [id" + replyMessage.getFromId() + "|" + replySenderUser.getFirstName()[0] + " " + replySenderUser.getLastName()[0] + "] отсутствует в этой беседе.")
                    .execute();
            return;
        }

        if (replySenderUserChat.getUserId() == cache.getSenderUserChat().getUserId()) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❌ Выдавать репутацию самому себе запрещено.").execute();
            return;
        }

        if (!cache.getSenderUserChat().isBanrep()) {
            if (!replySenderUserChat.isBanrep()) {
                chatRepository.giveReputation(replyMessage.getFromId(), peerId, replySenderUserChat.getReputation() + 1);
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("⚡ [id" + replyMessage.getFromId() + "|" +
                                replySenderUser.getFirstName()[0] + " " + replySenderUser.getLastName()[0] +
                                "] получил +1 к репутации.")
                        .execute();
            } else {
                vk.messages.send()
                        .setPeerId(peerId)
                        .setDisableMentions(true)
                        .setMessage("❗ [id" + replyMessage.getFromId() + "|" + replySenderUser.getFirstName()[0] + " " + replySenderUser.getLastName()[0]
                                + "] не может получать репутацию, так как у него бан репутации.")
                        .execute();
            }
        } else {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ [id" + message.getFromId() + "|" +
                            cache.getSenderUserChat().getNickname() + "], вы не можете выдавать репутацию, так как у вас бан репутации.")
                    .execute();
        }
    }
}