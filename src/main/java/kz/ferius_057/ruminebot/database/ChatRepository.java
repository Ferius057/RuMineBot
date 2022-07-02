package kz.ferius_057.ruminebot.database;

import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

import java.util.List;
import java.util.Set;

/**
 * @author Charles_Grozny
 */
public interface ChatRepository {

    // Создаёт беседу в таблице PEERIDS | таблица PEERIDS
    void createChat(final int peerId);


    // Добавляет пользователя в беседу | USERS
    void addUserInPeerId(final int userId, final int peerId, final String nickname, final int role);

    // Обновляет данные пользователя в беседе | USERS
    void updateUser(final int userId, final int peerId, final String nickname, final int role, final int exist);

    // Обновляет данные пользователя есть ли он в беседе или нет | USERS
    void updateExist(final int userId, final int peerId, final boolean exist);

    // Обновляет роль пользователя в беседе | USdERS
    void updateRole(final int userId, final int peerId, final int role);

    // Обновляет данные о блокировки репутации пользователя в беседе | USERS
    void updateBanReputation(final int userId, final int peerId, final boolean banrep);

    // Обновляет данные пользователя в беседе | USERS
    void giveReputation(final int userId, final int peerId, final int reputation);

    // Получает все админов из беседы | USERS
    List<UserChat> getAdminsFromChat(final int peerId);

    // Получает всех пользователей из беседы | USERS
    List<UserChat> getUsersFromChat(final int peerId);

    // Получает пользователя из беседы | USERS
    UserChat getUserFromChat(final int userId, final int peerId);


    // Добавляет пользователя в базу бота | USERSDATA
    void registerUserInBot(final int userId, final String[] firstName, final String[] lastName);

    // Получает пользователя из базы бота | USERSDATA
    User getUser(final int userId);



    Set<Integer> getChats();
    Set<Integer> getUsers();
}