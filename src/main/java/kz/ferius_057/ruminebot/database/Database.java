package kz.ferius_057.ruminebot.database;

import lombok.val;

import java.sql.*;

public final class Database {

    private final Connection connection;

    public Database(final Connection connection) {
        this.connection = connection;
    }

    public static Database create(final String location) throws SQLException {

        return new Database(DriverManager.getConnection("jdbc:h2:./" + location + ";auto_server=true"));
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void executeUpdate(final String query, final Object... parameters) {
        try (val statement = connection.prepareStatement(query)) {
            fillStatement(statement, parameters);

            statement.executeUpdate();
        } catch (SQLException e) {
            if (!e.getMessage().contains("Нарушение уникального индекса или первичного ключа")) {
                throw new RuntimeException(e);
            }
        }
    }

    private void fillStatement(final PreparedStatement statement, final Object[] parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
        }
    }
    public <T> T executeQuery(final ThrowableFunction<ResultSet, T, SQLException> reader, final String query, final Object... parameters) {
        try (val statement = connection.prepareStatement(query)) {
            fillStatement(statement, parameters);

            try (val rs = statement.executeQuery()) {
                return reader.apply(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}