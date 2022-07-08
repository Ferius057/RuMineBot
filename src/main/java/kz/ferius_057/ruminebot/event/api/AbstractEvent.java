package kz.ferius_057.ruminebot.event.api;

import api.longpoll.bots.methods.VkBotsMethods;
import kz.ferius_057.ruminebot.Main;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.database.ChatRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class AbstractEvent implements Event {

    Manager manager;

    VkBotsMethods vk;
    ChatRepository chatRepository;

    protected AbstractEvent() {
        this.manager = Main.getManager();

        this.vk = manager.vk();
        this.chatRepository = manager.chatRepository();
    }
}