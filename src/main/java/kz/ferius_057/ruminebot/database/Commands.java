package kz.ferius_057.ruminebot.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Commands {
    private static final Manager MANAGER = new Manager();

    public static void createTable(String nameTable) {
        try {
            switch (nameTable) {
                case "peerIds": MANAGER.executeUpdate("CREATE TABLE peerIds (" +
                            "peerId INTEGER," +
                            "countAdmins INTEGER," +
                            "countUsers INTEGER," +
                            "date INTEGER" +
                            ");");
                    System.out.println("Created peerIds table..."); break;
                case "users": MANAGER.executeUpdate("CREATE TABLE users (" +
                            "peerIdUserId INTEGER," +
                            "nickname TEXT," +
                            "role TEXT," +
                            "reputation INTEGER" +
                            ");");
                    System.out.println("Created users table..."); break;
                case "usersData": MANAGER.executeUpdate("CREATE TABLE usersData (" +
                            "userId INTEGER," +
                            "firstName TEXT," +
                            "lastName TEXT," +
                            "date INTEGER" +
                            ");");
                    System.out.println("Created usersData table..."); break;
                default: break;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static boolean checkTable(String nameTable) {
        return !(boolean) MANAGER.executeQuery("checkTable","SELECT name FROM sqlite_master WHERE type='table' AND name='" + nameTable + "';");
    }

    public static void addPeerId(int peerId, int count_admins, int count_users) {
        try {
            MANAGER.executeUpdate("INSERT INTO peerIds (peerId, countAdmins, countUsers, date)" +
                    "VALUES ("+peerId+","+count_admins+","+count_users+","+System.currentTimeMillis()+");");
            System.out.println("Adding peerId: " + peerId);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void getChats() {
        MANAGER.executeQuery("getChats","SELECT peerId FROM peerIds;");
    }
}