package kz.ferius_057.ruminebot.event.api;

import api.longpoll.bots.methods.VkBotsMethods;
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
    MessageActionStatus eventName;
    ChatRepository chatRepository;

    protected AbstractEvent(final Manager manager, final MessageActionStatus eventName) {
        this.manager = manager;
        this.eventName = eventName;
        this.vk = manager.vk();
        this.chatRepository = manager.chatRepository();
    }
}