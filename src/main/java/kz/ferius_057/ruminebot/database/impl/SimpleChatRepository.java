package kz.ferius_057.ruminebot.database.impl;

import kz.ferius_057.ruminebot.database.ChatRepository;
import kz.ferius_057.ruminebot.database.Database;
import kz.ferius_057.ruminebot.object.basic.User;
import kz.ferius_057.ruminebot.object.basic.UserChat;
import lombok.val;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Charles_Grozny
 */
public class SimpleChatRepository implements ChatRepository {

    private final Database database;

    public SimpleChatRepository(Database database) {
        this.database = database;

        createTables();
    }

    public void createTables() {
        database.executeUpdate("CREATE TABLE IF NOT EXISTS peerIds (" +
                "peerId INTEGER PRIMARY KEY," +
                "date LONG" +
                ");");
        database.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "userId INTEGER," +
                "peerId INTEGER," +
                "role INTEGER," +
                "reputation INTEGER," +
                "exist BOOLEAN," +
                "banrep BOOLEAN" +
                ");");
        database.executeUpdate("CREATE TABLE IF NOT EXISTS usersData (" +
                "userId INTEGER PRIMARY KEY," +
                "firstName TEXT ARRAY," +
                "lastName TEXT ARRAY," +
                "github TEXT," +
                "nicknameMinecraft TEXT," +
                "date LONG" +
                ");");
    }

    @Override
    public Set<Integer> getChats() {
        return database.executeQuery(
                rs -> {
                    val result = new HashSet<Integer>();

                    while (rs.next()) result.add(rs.getInt("peerId"));
                    return result;
                },
                "SELECT peerId FROM peerIds;"
        );
    }

    @Override
    public Set<Integer> getUsers() {
        return database.executeQuery(
                rs -> {
                    val result = new HashSet<Integer>();

                    while (rs.next()) result.add(rs.getInt("userId"));

                    return result;
                },
                "SELECT userId FROM usersData;"
        );
    }

    @Override
    public void createChat(int peerId) {
        database.executeUpdate("INSERT INTO peerIds (peerId, date)" +
                "VALUES (?, ?)", peerId, System.currentTimeMillis());
    }

    @Override
    public void addUserInPeerId(int userId, int peerId, int role) {
        database.executeUpdate("INSERT INTO users (userId, peerId, role, reputation, exist, banrep)" +
                "VALUES (?, ?, ?, ?, ?, ?)", userId, peerId, role, 0, true, false);
    }

    @Override
    public void updateUserChat(int userId, int peerId, int role, boolean exist) {
        database.executeUpdate("UPDATE users SET role=(?), exist=(?) " +
                "WHERE userId=(?) and peerId=(?)", role, exist, userId, peerId);
    }

    @Override
    public void updateExist(int userId, int peerId, boolean exist) {
        database.executeUpdate("UPDATE users SET exist=(?) " +
                "WHERE userId=(?) and peerId=(?)", exist, userId, peerId);
    }

    @Override
    public void updateRole(int userId, int peerId, int role) {
        database.executeUpdate("UPDATE users SET role=(?) " +
                "WHERE userId=(?) and peerId=(?)", role, userId, peerId);
    }

    @Override
    public void updateBanReputation(int userId, int peerId, boolean banrep) {
        database.executeUpdate("UPDATE users SET banrep=(?) " +
                "WHERE userId=(?) and peerId=(?)", banrep, userId, peerId);
    }

    @Override
    public void setReputation(int userId, int peerId, int reputation) {
        database.executeUpdate("UPDATE users SET reputation=(?) " +
                "WHERE userId=(?) and peerId=(?)", reputation, userId, peerId);
    }

    @Override
    public List<UserChat> getUsersFromChat(int peerId) {
        return database.executeQuery(
                rs -> {
                    List<UserChat> userInPeerId = new ArrayList<>();

                    while (rs.next()) {
                        userInPeerId.add(
                                new UserChat(
                                        rs.getInt(1),
                                        rs.getInt(2),
                                        rs.getInt(3),
                                        rs.getInt(4),
                                        rs.getBoolean(5),
                                        rs.getBoolean(6)
                                )
                        );
                    }

                    return userInPeerId;
                },
                "SELECT * FROM users WHERE peerId=(?)", peerId
        );
    }

    @Override
    public UserChat getUserFromChat(int userId, int peerId) {
        return database.executeQuery(
                rs -> {
                    UserChat userInPeerId = null;

                    while (rs.next()) {
                        userInPeerId = new UserChat(
                                rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getBoolean(5),
                                rs.getBoolean(6)
                        );
                    }

                    return userInPeerId;
                },
                "SELECT * FROM users WHERE userId=(?) and peerId=(?)", userId, peerId
        );
    }


    @Override
    public void registerUserInBot(int userId, final String[] firstName, final String[] lastName) {
        database.executeUpdate("INSERT INTO usersData (userId, firstName, lastName, github, nicknameMinecraft, date)" +
                "VALUES (?, ?, ?, ?, ?, ?)", userId, firstName, lastName, "false", "false", System.currentTimeMillis());
    }

    @Override
    public void updateUser(int userId, Object[] firstName, Object[] lastName, String github, String nicknameMinecraft) {
        database.executeUpdate("UPDATE usersData SET firstName=(?), lastName=(?), github=(?), nicknameMinecraft=(?) " +
                "WHERE userId=(?) ", firstName, lastName, github, nicknameMinecraft, userId);
    }

    @Override
    public User getUser(int userId) {
        return database.executeQuery(
                rs -> {
                    User user = null;

                    while (rs.next()) {
                        user = new User(
                                rs.getInt(1),
                                (Object[]) rs.getArray(2).getArray(),
                                (Object[]) rs.getArray(3).getArray(),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getLong(6)
                        );
                    }

                    return user;
                },
                "SELECT * FROM usersData WHERE userId=(?)", userId
        );
    }
}