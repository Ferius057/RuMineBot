package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.Permission;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.util.AutoUpdateUser;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Charles_Grozny
 */
@Permission(value = 1)
@CommandAnnotation(aliases = {"resync", "обновить", "update"})
public class Resync extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        val response = AutoUpdateUser.updateChatUsers(peerId, chatRepository, vk);

        vk.messages.send()
                .setPeerId(peerId)
                .setMessage("Данные беседы были обновлены.")
                .execute();

        User.registrationUser(manager, response.getProfiles().stream()
                .map(api.longpoll.bots.model.objects.basic.User::getId)
                .map(Object::toString).collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}