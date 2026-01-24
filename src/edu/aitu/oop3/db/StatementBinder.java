package edu.aitu.oop3.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementBinder {
    void bind(PreparedStatement ps) throws SQLException;
}
