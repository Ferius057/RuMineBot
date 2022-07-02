package kz.ferius_057.ruminebot.command;

import kz.ferius_057.ruminebot.Manager;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.exceptions.VkApiException;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Admins extends AbstractCommand {

    public Admins(Manager Manager) {
        super(Manager, "admins", "админы");
    }

    @Override
    public void run(User sender, Message message, String[] args) throws VkApiException {
        List<UserChat> admins = chatRepository.getAdminsFromChat(message.getPeerId());
        StringBuilder sb = new StringBuilder("✅ Список администраторов:\n");
        for (int i = 0; i < admins.size(); i++) {
            User user = User.get(manager, admins.get(i).getUserId());
            sb.append(i + 1).append(". [id").append(user.getUserId()).append("|").append(user.getFirstName()[0]).append(" ").append(user.getLastName()[0]).append("].\n");
        }
        vk.messages.send().setPeerId(message.getPeerId()).setDisableMentions(true)
                .setMessage(sb.toString()).execute();
    }
}