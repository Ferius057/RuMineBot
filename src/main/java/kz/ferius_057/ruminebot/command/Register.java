package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetConversationMembers;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.model.response.ExtendedVkList;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.object.User;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

@CommandAnnotation(aliases = { "reg", "register" })
public final class Register extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        if (manager.getPeerIds().add(peerId)) {
            long start = System.currentTimeMillis();

            ExtendedVkList<GetConversationMembers.Response.Item> response;

            response = vk.messages.getConversationMembers()
                    .setPeerId(peerId)
                    .setFields("first_name_nom")
                    .execute().getResponseObject();

            ArrayList<String> users = new ArrayList<>(), admins = new ArrayList<>();

            response.getItems()
                    .stream()
                    .filter(item -> item.getAdmin() != null)
                    .forEach(item -> admins.add(item.getMemberId().toString()));

            response.getProfiles().forEach(profile -> {
                val id = profile.getId();
                users.add(id.toString());

                chatRepository.addUserInPeerId(id, peerId, profile.getFirstName(), admins.contains(id.toString()) ? 1 : 0);
            });

            for (int i = 0; i < users.size(); i++) {
                if (!manager.getUsers().add(Integer.valueOf(users.get(i)))) {
                    users.remove(users.get(i));
                    i--;
                }
            }

            //CompletableFuture.runAsync(() -> {
            if (users.size() > 0) User.registrationUser(manager, users.toArray(new String[0]));
            //});

            chatRepository.createChat(peerId);

            vk.messages.send()
                    .setPeerId(peerId)
                    .setMessage("Ваша беседа успешно зарегистрирована.\nОбработал команду за " + (System.currentTimeMillis() - start) + "ms.")
                    .execute();
        } else {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setMessage("Ваша беседа уже зарегистрирована!")
                    .execute();
        }
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}