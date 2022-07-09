package kz.ferius_057.ruminebot.command.reputation;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import lombok.val;

import java.util.List;

/**
 * @author Charles_Grozny
 */
@CommandAnnotation(aliases = { "+reputation", "+rep", "+репутация", "+реп" })
public class ReputationAdd extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        val peerId = message.getPeerId();
        val replySenderUser = cache.getReplySenders().get(0);
        val replySenderUserChat = cache.getReplySendersUserChat().get(0);

        String msg;
        if (replySenderUserChat != null && replySenderUserChat.isExist()) {
            if (replySenderUserChat.getUserId() != cache.getSenderUserChat().getUserId()) {
                if (!cache.getSenderUserChat().isBanrep()) {
                    val push = replySenderUser.getFullName().get(0).getPush();
                    if (!replySenderUserChat.isBanrep()) {
                        val reputation = replySenderUserChat.getReputation();

                        chatRepository.setReputation(replyMessages.get(0).getFromId(), peerId, reputation + 1);
                        msg = "⚡ " + push + " получил +1 к репутации." + "\n⋞ " + reputation + " ⋟  ➤  ⋞ " + (reputation + 1) + " ⋟";
                    } else msg = "❗ " + push + " не может получать репутацию, так как у него бан репутации.";
                } else msg = "❗ [id" + message.getFromId() + "|" + cache.getSender().getFirstName()[0] + "], вы не можете выдавать репутацию, так как у вас бан репутации.";
            } else msg = "❌ Выдавать репутацию самому себе запрещено.";
        } else msg = "❌ " + replySenderUser.getFullName().get(0).getPush() + " отсутствует в этой беседе.";

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();
    }
}