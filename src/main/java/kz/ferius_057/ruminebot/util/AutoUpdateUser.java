package kz.ferius_057.ruminebot.util;

import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.methods.impl.messages.GetConversationMembers;
import api.longpoll.bots.model.response.ExtendedVkList;
import kz.ferius_057.ruminebot.database.ChatRepository;
import kz.ferius_057.ruminebot.object.UserChat;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 09.07.2022 | 19:22 ⭐
 */
@UtilityClass
public class AutoUpdateUser {

    @SneakyThrows
    public ExtendedVkList<GetConversationMembers.ResponseBody.Item> updateChatUsers(int peerId, ChatRepository chatRepository, VkBotsMethods vk) {
        val response = vk.messages.getConversationMembers()
                .setPeerId(peerId).execute().getResponse();

        val usersFromChat = chatRepository.getUsersFromChat(peerId);
        List<Integer> users = usersFromChat
                .stream().map(UserChat::getUserId).collect(Collectors.toList());
        List<Integer> admins = usersFromChat
                .stream().filter(user -> user.getRole() > 0).map(UserChat::getUserId).collect(Collectors.toList());

        response.getItems()
                .stream().filter(item -> item.getMemberId() > 0)
                .forEach(item -> {
                    if (users.contains(item.getMemberId()))
                        chatRepository.updateUserChat(item.getMemberId(), peerId,
                                item.isAdmin() ? 1 : (admins.contains(item.getMemberId()) ? 1 : 0), true);
                    else
                        chatRepository.addUserInPeerId(item.getMemberId(), peerId, item.isAdmin() ? 1 : 0);
                });

        users.removeAll(response.getItems()
                .stream().map(GetConversationMembers.ResponseBody.Item::getMemberId).filter(memberId -> memberId > 0).collect(Collectors.toList()));
        users.forEach(user -> chatRepository.updateExist(user, peerId, false));

        return response;
    }
}