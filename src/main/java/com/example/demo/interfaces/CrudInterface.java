package com.example.demo.interfaces;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface CrudInterface<T> {
    List<T> findAll();
    T findById(int id);
    void delete(int id) throws SQLException;
    abstract T createElementFromResultSet(ResultSet rs) throws SQLException;
}
