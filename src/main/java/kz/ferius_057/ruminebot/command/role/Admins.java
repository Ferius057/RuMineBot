package kz.ferius_057.ruminebot.command.role;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.object.User;
import lombok.val;

import java.util.List;

/**
 * @author Charles_Grozny
 */
@CommandAnnotation(aliases = { "admins", "админы", "staff", "staffs" })
public class Admins extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        val admins = chatRepository.getAdminsFromChat(message.getPeerId());

        val sb = new StringBuilder("✅ Список администраторов:\n");
        for (int i = 0; i < admins.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(User.get(manager, admins.get(i).getUserId()).getFullName().get(0).getPush())
                    .append(".\n");
        }

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setDisableMentions(true)
                .setMessage(sb.toString())
                .execute();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}