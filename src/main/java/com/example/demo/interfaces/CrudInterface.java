package com.example.demo.interfaces;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudInterface<T> {
    List<T> findAll();
    T findById(int id);
    T update();
    void delete(int id);
    abstract T createElementFromResultSet(ResultSet rs) throws SQLException;
}
