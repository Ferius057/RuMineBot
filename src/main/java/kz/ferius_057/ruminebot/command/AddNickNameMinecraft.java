package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;

/**
 * @author Charles_Grozny
 */
public class AddNickNameMinecraft extends AbstractCommand {

    public AddNickNameMinecraft(Manager Manager) {
        super(Manager, "minenick", "minenickname", "minecraftnickname", "майнник", "маинник");
    }

    @Override
    public void run(User sender, Message message, String[] args) throws VkApiException {
        vk.messages.send().setPeerId(message.getPeerId())
                .setMessage("❌ Данная команда находится в разработке.").execute();
    }
}