package kz.ferius_057.ruminebot;

import api.longpoll.bots.methods.VkBotsMethods;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.database.ChatRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 26.06.2022 | 18:09 ⭐
 */

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ManagerImpl implements Manager {

    ChatRepository chatRepository;

    Set<Integer> peerIds;
    Set<Integer> users;

    VkBotsMethods vk;

    LocalData localData;

    @Override
    public ChatRepository chatRepository() {
        return chatRepository;
    }

    @Override
    public Set<Integer> getPeerIds() {
        return peerIds;
    }

    @Override
    public Set<Integer> getUsers() {
        return users;
    }

    @Override
    public VkBotsMethods vk() {
        return vk;
    }

    @Override
    public LocalData localData() {
        return localData;
    }
}