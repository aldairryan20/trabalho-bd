package com.example.demo.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface EntityFactory<T> {
    T createFromResultSet(ResultSet rs) throws SQLException;
}
