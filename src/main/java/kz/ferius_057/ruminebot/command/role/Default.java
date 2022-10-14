package kz.ferius_057.ruminebot.command.role;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.impl.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.model.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.Permission;
import lombok.val;

import java.util.List;

/**
 * @author Charles_Grozny
 */

@Permission(value = 1)
@CommandAnnotation(aliases = { "default", "участник" })
public class Default extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        val peerId = message.getPeerId();
        val replySenderUserChat = cache.getReplySendersUserChat().get(0);
        val push = cache.getReplySenders().get(0).getFullName().get(0).getPush();

        String msg;
        if(replySenderUserChat != null) {
            if(replySenderUserChat.getRole() != 0) {
                chatRepository.updateRole(replyMessages.get(0).getFromId(), peerId, 0);
                msg = "✅ " + push + " теперь участник.";
            } else msg = "❗ " + push + " уже имеет роль участника.";
        } else msg = "❌ " + push + " отсутствует в этой беседе.";

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();
    }
}