package kz.ferius_057.ruminebot.command.reputation;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.object.User;
import lombok.val;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Charles_Grozny
 */
@CommandAnnotation(aliases = { "reptop", "рептоп" })
public class ReputationTop extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        val top = chatRepository.getUsersFromChat(message.getPeerId())
                .stream()
                .filter(s -> s.getReputation() >= 1)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        String msg;
        if (top.size() != 0) {
            val text = new StringBuilder("⭐ Топ репутации пользователей в беседе:\n");
            for (int i = 0; i < top.size(); i++) {
                text.append(i + 1).append(". ")
                        .append(User.get(manager, top.get(i).getUserId()).getFullName().get(0).getPush())
                        .append(" - ")
                        .append(top.get(i).getReputation())
                        .append(" реп.\n");
            }

            msg = text.toString();
        } else msg = "⭐ В беседе у всех 0 репутации.";

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();
    }
}