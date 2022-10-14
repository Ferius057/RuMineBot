package kz.ferius_057.ruminebot.command.api.impl;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Main;
import kz.ferius_057.ruminebot.manager.Manager;
import kz.ferius_057.ruminebot.command.api.Command;
import kz.ferius_057.ruminebot.command.api.model.CacheDataMessage;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.database.ChatRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class AbstractCommand implements Command {

    Manager manager;

    VkBotsMethods vk;
    LocalData localData;
    ChatRepository chatRepository;

    protected AbstractCommand() {
        this.manager = Main.getManager();

        this.vk = manager.vk();
        this.localData = manager.localData();
        this.chatRepository = manager.chatRepository();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage("❗ Сообщение должно быть ответом на другое сообщение или пересланным сообщение.")
                .execute();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}