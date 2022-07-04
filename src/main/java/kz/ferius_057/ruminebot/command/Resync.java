package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetConversationMembers;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.model.response.ExtendedVkList;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.object.UserChat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Resync extends AbstractCommand {

    public Resync(Manager Manager) {
        super(Manager,"resync", "обновить", "update");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        int peerId = message.getPeerId();

        if (cache.getSenderUserChat().getRole() < 1) {
            vk.messages.send().setPeerId(message.getPeerId()).setDisableMentions(true)
                    .setMessage("❗ [id" + message.getFromId() + "|" + cache.getSenderUserChat().getNickname() + "], у вас недостаточно прав для данной команды.").execute();
        } else {
            ExtendedVkList<GetConversationMembers.Response.Item> response;

            response = vk.messages.getConversationMembers()
                    .setPeerId(peerId)
                    .setFields("first_name_nom")
                    .execute().getResponseObject();

            long start = System.currentTimeMillis();

            ArrayList<String> users = new ArrayList<>();
            List<Integer> admins = new ArrayList<>();

            response.getItems().forEach(s -> {
                if (s.getAdmin() != null) admins.add(s.getMemberId());
            });

            response.getProfiles().forEach(s -> {
                users.add(s.getId().toString());
                UserChat userChat = chatRepository.getUserFromChat(s.getId(), peerId);
                if (admins.contains(s.getId())) {
                    if (userChat == null)
                        chatRepository.addUserInPeerId(s.getId(), peerId, s.getFirstName(), 1);
                    else chatRepository.updateUser(s.getId(), peerId, userChat.getNickname(), 1, 1);
                } else {
                    if (userChat == null)
                        chatRepository.addUserInPeerId(s.getId(), peerId, s.getFirstName(), 0);
                    else chatRepository.updateUser(s.getId(), peerId, userChat.getNickname(), 0, 1);
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

            vk.messages.send()
                    .setPeerId(peerId)
                    .setMessage("Данные беседы были обновлены.\nОбработал команду за " + (System.currentTimeMillis() - start) + "ms.")
                    .execute();
        }
    }
}