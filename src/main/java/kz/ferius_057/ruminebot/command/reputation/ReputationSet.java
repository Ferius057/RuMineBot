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
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 06.07.2022 | 1:35 ⭐
 */

@Permission(value = 1)
public class ReputationSet extends AbstractCommand {

    public ReputationSet(Manager manager) {
        super(manager, "reputationset", "repset", "репсет", "setrep", "сетреп");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        User replySender = cache.getReplySenders().get(0);
        UserChat replySenderUserChat = cache.getReplySendersUserChat().get(0);

        if (replySenderUserChat == null || !replySenderUserChat.isExist()) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❌ " + replySender.getFullName().get(0).getPush() + " отсутствует в этой беседе.")
                    .execute();
            return;
        }

        if (args.length <= 0) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ Вы не указали кол-во репутации первым аргументом.")
                    .execute();
            return;
        }

        try {
            int repValue = Integer.parseInt(args[0]);

            chatRepository.setReputation(replySender.getUserId(), message.getPeerId(), repValue);
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("✴ " + replySender.getFullName().get(2).getPush() + " было установлено " + repValue + " репутации." +
                            "\n⋞ " + replySenderUserChat.getReputation() + " ⋟  ➤  ⋞ " + (repValue) + " ⋟")
                    .execute();
        } catch (NumberFormatException e) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ Вы указали неверное кол-во репутации.")
                    .execute();
        }
    }
}