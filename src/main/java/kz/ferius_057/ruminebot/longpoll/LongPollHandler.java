package kz.ferius_057.ruminebot.longpoll;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Main;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.ManagerImpl;
import kz.ferius_057.ruminebot.command.api.CommandManager;
import kz.ferius_057.ruminebot.command.api.SimpleCommandManager;
import kz.ferius_057.ruminebot.event.api.EventManager;
import kz.ferius_057.ruminebot.event.api.SimpleEventManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LongPollHandler extends LongPollBot {
    String token;

    @Getter
    Manager manager;

    CommandManager commandManager;
    EventManager eventManager;

    public LongPollHandler(final String token, final Manager managerOld) {
        this.token = token;

        manager = new ManagerImpl(
                managerOld.chatRepository(), managerOld.getPeerIds(), managerOld.getUsers(),
                vk, managerOld.localData()
        );
        Main.setManager(manager);

        CommandManager commandManager = SimpleCommandManager.create(manager);
        EventManager eventManager = SimpleEventManager.create();

        this.commandManager = commandManager;
        this.eventManager = eventManager;
    }

    @Override
    public void onMessageNew(MessageNew messageNew) {
        Message message = messageNew.getMessage();
        if (message.hasText()) {
            System.out.println("[*] New message: " + message.getText()
                    + " | " + message.getPeerId()
                    + " | " + message.getFromId() + " - " + message);

            if (!commandManager.run(message)) {
              //  if (message.getAction() != null) {        | бот не может получать ивенты о входах и выходах
                    eventManager.run(message, message.getAction());
              //  }
            }
        }
    }

    @Override
    public String getAccessToken() {
        return token;
    }
}