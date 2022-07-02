package kz.ferius_057.ruminebot;

import api.longpoll.bots.methods.VkBotsMethods;
import kz.ferius_057.ruminebot.data.LocalData;
import kz.ferius_057.ruminebot.database.ChatRepository;
import kz.ferius_057.ruminebot.database.ChatRepositoryImpl;

import java.util.Set;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 26.06.2022 | 18:08 ⭐
 */
public interface Manager {
    ChatRepository chatRepository();

    Set<Integer> getPeerIds();
    Set<Integer> getUsers();

    VkBotsMethods vk();

    LocalData localData();
}