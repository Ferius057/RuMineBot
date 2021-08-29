package kz.ferius_057.ruminebot.database;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public final class Database {
    private final DataSource dataSource;

    public Database(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static Database create(final String location) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:" + location);

        return new Database(dataSource);
    }

    public void executeUpdate(
            final String query,
            final Object... parameters
    ) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            fillStatement(statement, parameters);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillStatement(final PreparedStatement statement, final Object[] parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
        }
    }
    public <T> T executeQuery(
            final ThrowableFunction<ResultSet, T, SQLException> reader,
            final String query,
            final Object... parameters
    ) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            fillStatement(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {
                return reader.apply(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}