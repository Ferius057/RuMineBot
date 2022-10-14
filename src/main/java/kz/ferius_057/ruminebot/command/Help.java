package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.impl.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.model.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.ExceptRegistered;

/**
 * @author Charles_Grozny
 */
@ExceptRegistered
@CommandAnnotation(aliases = { "help", "помощь", "commands", "команды" })
public class Help extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage("\uD83D\uDCDD Список команд: vk.com/@rumine_coders-help")
                .setAttachment("article-204964592_46752_e23fdbd46491368d8a")
                .execute();
    }
}