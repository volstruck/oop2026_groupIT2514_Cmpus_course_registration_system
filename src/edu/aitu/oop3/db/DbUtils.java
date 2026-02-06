package edu.aitu.oop3.db;

import java.sql.*;

public class DbUtils {

    private static final JdbcConfig CONFIG = JdbcConfig.getInstance();

    private DbUtils() {}

    public static <T> T execQuery(
            String sql,
            StatementBinder binder,
            ResultSetHandler<T> handler
    ) {
        try (Connection con = DriverManager.getConnection(
                CONFIG.getUrl(),
                CONFIG.getUser(),
                CONFIG.getPassword());
             PreparedStatement ps = con.prepareStatement(sql)) {

            binder.bind(ps);
            try (ResultSet rs = ps.executeQuery()) {
                return handler.extract(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to run sql query", e);
        }
    }

    public static void execUpdate(
            String sql,
            StatementBinder binder
    ) {
        try (Connection con = DriverManager.getConnection(
                CONFIG.getUrl(),
                CONFIG.getUser(),
                CONFIG.getPassword());
             PreparedStatement ps = con.prepareStatement(sql)) {

            binder.bind(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to run sql update", e);
        }
    }
}
