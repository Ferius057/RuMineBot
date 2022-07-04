package kz.ferius_057.ruminebot.command.api;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.database.ChatRepository;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class AbstractCommand implements Command {
    String name;
    String[] aliases;

    Manager manager;

    ChatRepository chatRepository;
    VkBotsMethods vk;
    LocalData localData;

    protected AbstractCommand(final Manager manager, final String name, final String... aliases) {
        this.name = name;
        this.aliases = aliases;

        this.manager = manager;

        this.chatRepository = manager.chatRepository();
        this.vk = manager.vk();
        this.localData = manager.localData();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {}

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {}


    protected final List<Message> getForeignMessage(final Message message, final int amount) {
        List<Message> messages = message.getFwdMessages();
        messages.add(0, message.getReplyMessage());

        return messages.subList(0, amount);
    }
}