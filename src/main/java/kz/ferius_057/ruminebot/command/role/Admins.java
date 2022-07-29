package kz.ferius_057.ruminebot.command.role;

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
@CommandAnnotation(aliases = { "admins", "админы", "staff", "staffs" })
public class Admins extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        val admins = chatRepository.getUsersFromChat(message.getPeerId())
                .stream()
                .filter(userChat -> userChat.getRole() > 0)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        val sb = new StringBuilder("✅ Список администраторов:\n");
        for (int i = 0; i < admins.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(User.get(manager, admins.get(i).getUserId()).getFullName().get(0).getPush())
                    .append("\n");
        }

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setDisableMentions(true)
                .setMessage(sb.toString())
                .execute();
    }
}