package kz.ferius_057.ruminebot.database;

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
                "date INTEGER" +
                ");");
        database.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "peerIdUserId INTEGER," +
                "nickname TEXT," +
                "role TEXT," +
                "reputation INTEGER" +
                ");");

        database.executeUpdate("CREATE TABLE IF NOT EXISTS usersData (" +
                "userId INTEGER," +
                "firstName TEXT," +
                "lastName TEXT," +
                "date INTEGER" +
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


}