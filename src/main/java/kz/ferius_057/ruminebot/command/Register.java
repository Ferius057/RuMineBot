package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetConversationMembers;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.model.response.ExtendedVkList;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;

import java.util.ArrayList;

public final class Register extends AbstractCommand {
    public Register(Manager Manager) {
        super(Manager,"reg", "register");
    }

    @Override
    public void run(User sender, Message message, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        if (manager.getPeerIds().add(peerId)) {
            ExtendedVkList<GetConversationMembers.Response.Item> response;

            response = vk.messages.getConversationMembers()
                    .setPeerId(peerId)
                    .setFields("first_name_nom")
                    .execute().getResponseObject();

            long start = System.currentTimeMillis();

            ArrayList<String> users = new ArrayList<>();
            ArrayList<String> admins = new ArrayList<>();

            response.getItems().forEach(s -> {
                if (s.getAdmin() != null) admins.add(s.getMemberId().toString());
            });

            response.getProfiles().forEach(s -> {
                users.add(s.getId().toString());
                if (admins.contains(s.getId().toString())) {
                    chatRepository.addUserInPeerId(s.getId(), peerId, s.getFirstName(), 1);
                } else {
                    chatRepository.addUserInPeerId(s.getId(), peerId, s.getFirstName(), 0);
                }
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
}