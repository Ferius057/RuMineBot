package kz.ferius_057.ruminebot.database;

import java.sql.SQLException;

public class Commands {
    private static final Manager MANAGER = new Manager();

    public static void createTable(String nameTable) {
        try {
            switch (nameTable) {
                case "peer_ids": MANAGER.executeUpdate("CREATE TABLE peerIds (" +
                            "peerId INTEGER," +
                            "count_admins INTEGER," +
                            "count_users INTEGER," +
                            "date INTEGER" +
                            ");");
                    System.out.println("Created peer_ids table..."); break;
                case "users": MANAGER.executeUpdate("CREATE TABLE users (" +
                            "peerId_userId INTEGER," +
                            "nickname TEXT," +
                            "role TEXT," +
                            "reputation INTEGER" +
                            ");");
                    System.out.println("Created users table..."); break;
                case "users_data": MANAGER.executeUpdate("CREATE TABLE users_data (" +
                            "userId INTEGER," +
                            "firstName TEXT," +
                            "lastName TEXT," +
                            "date INTEGER" +
                            ");");
                    System.out.println("Created users_data table..."); break;
                default: break;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static boolean checkTable(String nameTable) {
       /* try (ResultSet resultSet = MANAGER.executeQuery("SELECT * FROM " + nameTable + ";")) {
            return resultSet == null;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }*/
        return true;
    }

    public static void addPeerId(int peerId, int count_admins, int count_users) {
        try {
            MANAGER.executeUpdate("INSERT INTO peerIds (peerId, count_admins, count_users, date)" +
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