package kz.ferius_057.ruminebot.database;

import kz.ferius_057.ruminebot.command.tool.User;
import kz.ferius_057.ruminebot.command.tool.UserInPeerId;

import java.sql.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public final class ChatDao {

    private final Database database;

    private ChatDao(final Database database) {
        this.database = database;
    }

    public static ChatDao create(final Database database) {
        return new ChatDao(database);
    }

    public void createTables() {
        database.executeUpdate("CREATE TABLE IF NOT EXISTS peerIds (" +
                "peerId INTEGER PRIMARY KEY," +
                "countAdmins INTEGER," +
                "countUsers INTEGER," +
                "date LONG" +
                ");");
        database.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "peerIdUserId LONG PRIMARY KEY," +
                "nickname TEXT," +
                "role TEXT," +
                "reputation INTEGER," +
                "exist BOOLEAN," +
                "banrep BOOLEAN" +
                ");");
        database.executeUpdate("CREATE TABLE IF NOT EXISTS usersData (" +
                "userId INTEGER PRIMARY KEY," +
                "firstName ARRAY," +
                "lastName ARRAY," +
                "github TEXT," +
                "nicknameMinecraft TEXT," +
                "date LONG" +
                ");");
    }

    public void addPeerId(final int peerId, final int count_admins, final int count_users) {
        database.executeUpdate("INSERT INTO peerIds (peerId, countAdmins, countUsers, date)" +
                "VALUES (?, ?, ?, ?)", peerId, count_admins, count_users, System.currentTimeMillis());
    }

    public Set<Integer> getChats() {
        return database.executeQuery(
                rs -> {
                    Set<Integer> result = new HashSet<>();

                    while (rs.next()) {
                        result.add(rs.getInt("peerId"));
                    }

                    return result;
                },
                "SELECT peerId FROM peerIds;"
        );
    }

    public Set<Integer> getUsers() {
        return database.executeQuery(
                rs -> {
                    Set<Integer> result = new HashSet<>();

                    while (rs.next()) {
                        result.add(rs.getInt("userId"));
                    }

                    return result;
                },
                "SELECT userId FROM usersData;"
        );
    }


    public void addUserInPeerId(final String peerIdUserId, final String nickname, final String role) {
        database.executeUpdate("INSERT INTO users (peerIdUserId, nickname, role, reputation, exist, banrep)" +
                "VALUES (?, ?, ?, ?, ?, ?)", Long.parseLong(peerIdUserId.replace("_","")), nickname, role, 0, true, false);
    }

    public void updatePeerId(final String peerIdUserId, final String nickname, final String role, final int exist) {
        database.executeUpdate("UPDATE users SET nickname=(?), role=(?), exist=(?) " +
                "WHERE peerIdUserId=(?)", nickname, role, exist, Long.parseLong(peerIdUserId.replace("_","")));
    }

    public boolean isUserInPeerId(final String peerIdUserId) {
        return database.executeQuery(
                rs -> {
                    boolean exist = false;

                    while (rs.next()) {
                        exist = rs.getBoolean("exist");
                    }

                    return exist;
                },
                "SELECT * FROM users WHERE peerIdUserId=(?)", Long.parseLong(peerIdUserId.replace("_",""))
        );
    }

    public String wasUserInPeerId(final String peerIdUserId) {
        return database.executeQuery(
                rs -> {
                    String exist = "false";

                    while (rs.next()) {
                        exist = rs.getString(2);
                    }

                    return exist;
                },
                "SELECT * FROM users WHERE peerIdUserId=(?)", Long.parseLong(peerIdUserId.replace("_",""))
        );
    }

    public void updateExist(final String peerIdUserId, final boolean exist) {
        database.executeUpdate("UPDATE users SET exist=(?) " +
                "WHERE peerIdUserId=(?)", exist, Long.parseLong(peerIdUserId.replace("_","")));
    }

    public void updateBanReputation(final String peerIdUserId, final boolean banrep) {
        database.executeUpdate("UPDATE users SET banrep=(?) " +
                "WHERE peerIdUserId=(?)", banrep, Long.parseLong(peerIdUserId.replace("_","")));
    }

    public void giveReputation(final String peerIdUserId, final int reputation) {
        database.executeUpdate("UPDATE users SET reputation=(?) " +
                "WHERE peerIdUserId=(?)", reputation, Long.parseLong(peerIdUserId.replace("_","")));
    }

    public UserInPeerId getUserInPeerId(final String peerIdUserId) {
        return database.executeQuery(
                rs -> {
                    UserInPeerId userInPeerId = null;

                    while (rs.next()) {
                        userInPeerId = new UserInPeerId(rs.getLong(1), rs.getString(2), rs.getString(3),
                                        rs.getInt(4), rs.getBoolean(5), rs.getBoolean(6));
                    }

                    return userInPeerId;
                },
                "SELECT * FROM users WHERE peerIdUserId=(?)", Long.parseLong(peerIdUserId.replace("_",""))
        );
    }










    public void registrationUserInTheBot(final int userId, final String[] firstName, final String[] lastName) {
        database.executeUpdate("INSERT INTO usersData (userId, firstName, lastName, github, nicknameMinecraft, date)" +
                "VALUES (?, ?, ?, ?, ?, ?)", userId, firstName, lastName, "false", "false", System.currentTimeMillis());
    }

    public User getUser(final int userId) {
        return database.executeQuery(
                rs -> {
                    User user = null;

                    while (rs.next()) {
                        user = new User(rs.getInt(1), (Object[]) rs.getArray(2).getArray(), (Object[]) rs.getArray(3).getArray(),
                                rs.getString(4), rs.getString(5), rs.getLong(6));
                    }

                    return user;
                },
                "SELECT * FROM usersData WHERE userId=(?)", userId
        );
    }
}