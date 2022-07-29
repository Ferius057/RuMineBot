package kz.ferius_057.ruminebot.longpoll;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.model.events.messages.MessageNew;
import kz.ferius_057.ruminebot.command.api.CommandManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;


@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LongPollHandler extends LongPollBot {
    String token;
    CommandManager commandManager;

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