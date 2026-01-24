package edu.aitu.oop3.db;

import java.sql.*;

public class DbUtils {
    private static final JdbcConfig CONFIG =
            new JdbcConfig("postgres.huchyyjtkttzvljntkiy", "alisher09123478");

    private DbUtils() {}

    public static <T> T execQuery(String sql, StatementBinder binder, ResultSetHandler <T> h){
        try (Connection con = DriverManager.getConnection(
                CONFIG.toString(),
                CONFIG.getUser(),
                CONFIG.getPassword());
            PreparedStatement ps = con.prepareStatement(sql))
        {
            binder.bind(ps);
            ResultSet rs = ps.executeQuery();
            return h.extract(rs);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void execUpdate(String sql, StatementBinder binder){
        try (Connection con = DriverManager.getConnection(
                CONFIG.toString(),
                CONFIG.getUser(),
                CONFIG.getPassword());
             PreparedStatement ps = con.prepareStatement(sql);)
        {
            binder.bind(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}




//package campus.utils;
//
//import java.sql.*;
//
//public class DbUtils {
//    private static final JdbcConfig CONFIG =
//            new JdbcConfig("localhost", "campus", "volstruck", "alisher0505");
//
//    public static String execQuery(String query, String StudentName){
//        try (Connection con = DriverManager.getConnection(CONFIG.toString(),  CONFIG.getUser(), CONFIG.getPassword());
//             Statement st = con.createStatement();
//             ResultSet rs = st.executeQuery(query))
//        {
//            rs.next();
//            return rs.getString("name");
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}