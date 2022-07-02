package kz.ferius_057.ruminebot.command.api;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.database.tool.User;

import java.util.List;

public interface Command {
    String getName();
    String[] getAliases();

    void run(User sender, Message message, String[] args) throws VkApiException;

    void run(User sender, Message message, List<Message> replyMessages, String[] args) throws VkApiException;
}