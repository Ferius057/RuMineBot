package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;

/**
 * @author Charles_Grozny
 */
public class AddGithub extends AbstractCommand {

    public AddGithub(Manager Manager) {
        super(Manager, "github", "гитхаб");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        vk.messages.send().setPeerId(message.getPeerId())
                .setMessage("❌ Данная команда находится в разработке.").execute();
    }
}