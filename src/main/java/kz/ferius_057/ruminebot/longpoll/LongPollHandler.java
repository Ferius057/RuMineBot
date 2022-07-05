package kz.ferius_057.ruminebot.longpoll;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.ManagerImpl;
import kz.ferius_057.ruminebot.command.api.CommandManager;
import kz.ferius_057.ruminebot.command.api.SimpleCommandManager;
import kz.ferius_057.ruminebot.event.api.EventManager;
import kz.ferius_057.ruminebot.event.api.SimpleEventManager;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.logging.Logger;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LongPollHandler extends LongPollBot {
    String token;

    CommandManager commandManager;
    EventManager eventManager;

    public LongPollHandler(final String token, final Manager managerOld) {
        this.token = token;

        Manager manager = new ManagerImpl(
                managerOld.chatRepository(), managerOld.getPeerIds(), managerOld.getUsers(),
                vk, managerOld.localData()
        );

        CommandManager commandManager = SimpleCommandManager.create(manager);
        EventManager eventManager = SimpleEventManager.create(manager);

        this.commandManager = commandManager;
        this.eventManager = eventManager;

        System.out.println("Started!");
    }

    @Override
    public void onMessageNew(MessageNew messageNew) {
        Message message = messageNew.getMessage();
        if (message.hasText()) {
            System.out.println("[*] New message: " + message.getText()
                    + " | " + message.getPeerId()
                    + " | " + message.getFromId() + " - " + message);

            if (!commandManager.run(message)) {
                if (message.getAction() != null) {
                    eventManager.run(message, message.getAction());
                }
            }
        }
    }

    @Override
    public String getAccessToken() {
        return token;
    }
}