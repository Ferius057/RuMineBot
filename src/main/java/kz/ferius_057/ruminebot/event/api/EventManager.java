package kz.ferius_057.ruminebot.event.api;

import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAction;
import kz.ferius_057.ruminebot.command.api.Command;
import kz.ferius_057.ruminebot.data.LocalData;

public interface EventManager {
    boolean run(Message message, MessageAction action);

    void register(Event command);
}