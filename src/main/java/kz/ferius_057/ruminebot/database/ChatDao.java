package kz.ferius_057.ruminebot.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
                "exist BOOLEAN" +
                ");");
        database.executeUpdate("CREATE TABLE IF NOT EXISTS usersData (" +
                "userId INTEGER PRIMARY KEY," +
                "firstName TEXT," +
                "lastName TEXT," +
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
        database.executeUpdate("INSERT INTO users (peerIdUserId, nickname, role, reputation, exist)" +
                "VALUES (?, ?, ?, ?, ?)", Long.parseLong(peerIdUserId.replace("_","")), nickname, role, 0, 1);
    }

    public void updatePeerId(final String peerIdUserId, final String nickname, final String role,final int exist) {
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

    public void updateExist(final String peerIdUserId, final int exist) {
        database.executeUpdate("UPDATE users SET exist=(?) " +
                "WHERE peerIdUserId=(?)", exist, Long.parseLong(peerIdUserId.replace("_","")));
    }

    public void giveReputation(final String peerIdUserId, final int reputation) {
        database.executeUpdate("UPDATE users SET reputation=(?) " +
                "WHERE peerIdUserId=(?)", reputation, Long.parseLong(peerIdUserId.replace("_","")));
    }

    public int getReputation(final String peerIdUserId) {
        return database.executeQuery(
                rs -> {
                    int exist = 0;

                    while (rs.next()) {
                        exist = rs.getInt(4);
                    }

                    return exist;
                },
                "SELECT * FROM users WHERE peerIdUserId=(?)", Long.parseLong(peerIdUserId.replace("_",""))
        );
    }











    public void registrationUserInTheBot(final int userId, final String firstName, final String lastName) {
        database.executeUpdate("INSERT INTO usersData (userId, firstName, lastName, date)" +
                "VALUES (?, ?, ?, ?)", userId, firstName, lastName, System.currentTimeMillis());
    }
}