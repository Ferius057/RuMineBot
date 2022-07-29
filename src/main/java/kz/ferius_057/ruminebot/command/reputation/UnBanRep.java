package kz.ferius_057.ruminebot.command.reputation;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.Permission;
import lombok.val;

import java.util.List;

/**
 * @author Charles_Grozny
 */
@Permission(value = 1)
@CommandAnnotation(aliases = { "unbanrep", "repunban", "разбанреп", "репразбан" })
public class UnBanRep extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        val peerId = message.getPeerId();
        val replySenderUserChat = cache.getReplySendersUserChat().get(0);
        val fullName = cache.getReplySenders().get(0).getFullName();

        String msg;
        if(replySenderUserChat != null) {
            if(replySenderUserChat.isBanrep()) {
                chatRepository.updateBanReputation(replyMessages.get(0).getFromId(), peerId, false);
                msg = "✅ Снял бан репутации " + fullName.get(2).getPush() + ".";
            } else msg = "❗ У " + fullName.get(1).getPush() + " нету бана репутации.";
        } else msg = "❌ " + fullName.get(0).getPush() + " отсутствует в этой беседе.";

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();
    }
}