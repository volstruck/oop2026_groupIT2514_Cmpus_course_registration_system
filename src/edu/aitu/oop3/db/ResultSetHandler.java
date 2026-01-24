package edu.aitu.oop3.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
    T extract(ResultSet rs) throws SQLException;
}
