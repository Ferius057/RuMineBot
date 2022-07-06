package kz.ferius_057.ruminebot.command;

import kz.ferius_057.ruminebot.Manager;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.exceptions.VkApiException;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Help extends AbstractCommand {

    public Help(Manager Manager) {
        super(Manager, "help", "помощь", "commands", "команды");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        vk.messages.send().setPeerId(message.getPeerId())
                .setMessage("\uD83D\uDCDD Список команд: vk.com/@rumine_coders-help")
                .setAttachment("article-204964592_46752_e23fdbd46491368d8a").execute();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}