package kz.ferius_057.ruminebot.longpoll;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.model.events.messages.MessageNew;
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
import lombok.val;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LongPollHandler extends LongPollBot {
    String token;
    CommandManager commandManager;

    public LongPollHandler(final String token, final CommandManager commandManager) {
        this.token = token;
        this.commandManager = commandManager;
    }

    @Override
    public void onMessageNew(MessageNew messageNew) {
        val message = messageNew.getMessage();
        if (message.hasText()) {
            System.out.println("[*] New message: " + message.getText()
                    + " | " + message.getPeerId()
                    + " | " + message.getFromId() + " - " + message);

            commandManager.run(message);
        }
    }

    @Override
    public String getAccessToken() {
        return token;
    }
}