package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetConversationMembers;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.model.response.ExtendedVkList;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.ExceptRegistered;
import kz.ferius_057.ruminebot.object.User;

import java.util.List;
import java.util.stream.Collectors;


@ExceptRegistered()
@CommandAnnotation(aliases = {"reg", "register"})
public final class Register extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        String msg;
        if (!manager.getPeerIds().contains(peerId)) {
            ExtendedVkList<GetConversationMembers.Response.Item> response = vk.messages.getConversationMembers()
                    .setPeerId(peerId).execute().getResponseObject();

            response.getItems()
                    .stream()
                    .filter(item -> item.getMemberId() > 0)
                    .forEach(item -> {
                        System.out.println(item.getMemberId() + " - " + item.isAdmin() + " = " + (item.isAdmin() ? 1 : 0));
                        chatRepository.addUserInPeerId(item.getMemberId(),
                                peerId, item.isAdmin() ? 1 : 0);
                    });

            chatRepository.createChat(peerId);
            manager.getPeerIds().add(peerId);

            User.registrationUser(manager, response.getProfiles().stream()
                    .map(api.longpoll.bots.model.objects.basic.User::getId)
                    .map(Object::toString).collect(Collectors.toUnmodifiableList()));

            msg = "Ваша беседа успешно зарегистрирована.";
        } else {
            msg = "Ваша беседа уже зарегистрирована!";
        }

        vk.messages.send()
                .setPeerId(peerId)
                .setMessage(msg)
                .execute();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}