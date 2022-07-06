package kz.ferius_057.ruminebot.command.role;

import kz.ferius_057.ruminebot.Manager;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.exceptions.VkApiException;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.object.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Admins extends AbstractCommand {

    public Admins(Manager Manager) {
        super(Manager, "admins", "админы", "staff", "staffs");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        List<UserChat> admins = chatRepository.getAdminsFromChat(message.getPeerId());
        StringBuilder sb = new StringBuilder("✅ Список администраторов:\n");
        for (int i = 0; i < admins.size(); i++) {
            User user = User.get(manager, admins.get(i).getUserId());
            sb.append(i + 1).append(". ")
                    .append(user.getFullName().get(0).getPush())
                    .append(".\n");
        }
        vk.messages.send().setPeerId(message.getPeerId()).setDisableMentions(true)
                .setMessage(sb.toString()).execute();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}