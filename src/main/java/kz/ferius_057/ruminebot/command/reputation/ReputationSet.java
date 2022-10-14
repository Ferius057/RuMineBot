package kz.ferius_057.ruminebot.command.reputation;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.impl.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.model.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.MinimalArgs;
import kz.ferius_057.ruminebot.command.api.annotation.Permission;
import lombok.val;

import java.util.List;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 06.07.2022 | 1:35 ⭐
 */

@Permission(value = 1)
@MinimalArgs(value = 1, message = "!repset {кол-во репутации}")
@CommandAnnotation(aliases = { "reputationset", "repset", "репсет", "setrep", "сетреп" })
public class ReputationSet extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        val peerId = message.getPeerId();
        val replySender = cache.getReplySenders().get(0);
        val replySenderUserChat = cache.getReplySendersUserChat().get(0);

        String msg;
        if (replySenderUserChat != null && replySenderUserChat.isExist()) {
            if (args.length > 0) {
                try {
                    val repValue = Integer.parseInt(args[0]);

                    chatRepository.setReputation(replySender.getUserId(), peerId, repValue);

                    msg = "✴ " + replySender.getFullName().get(2).getPush() + " было установлено " + repValue + " репутации." +
                            "\n⋞ " + replySenderUserChat.getReputation() + " ⋟  ➤  ⋞ " + repValue + " ⋟";
                } catch (NumberFormatException e) {
                    msg = "❗ Вы указали неверное кол-во репутации.";
                }
            } else msg = "❗ Вы не указали кол-во репутации первым аргументом.";
        } else msg = "❌ " + replySender.getFullName().get(0).getPush() + " отсутствует в этой беседе.";

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();
    }
}