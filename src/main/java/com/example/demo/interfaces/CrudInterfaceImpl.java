package com.example.demo.interfaces;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.demo.config.SpringJdbcConfig;

public class CrudInterfaceImpl<T> implements CrudInterface<T> {
    private static final Logger logger = LogManager.getLogger(CrudInterfaceImpl.class);
    private String tableName;

    public CrudInterfaceImpl(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<T> findAll() {
        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        List<T> elements = new ArrayList<>();

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName + ";");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                var element = createElementFromResultSet(rs);
                elements.add(element);
            }
        } catch (SQLException e) {
            logger.error("Error at " + getClass().getName() + "\nMethod = " + methodName + "\n", e);
        }
        return elements;
    }

    public T createElementFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public T findById(int id) {
        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        var sql = "SELECT * FROM "+ tableName +" WHERE id = ?;";
        T element = null;
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                element = createElementFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.error("Error at " + getClass().getName() + "\nMethod = " + methodName + "\n", e.getMessage());
            e.printStackTrace();
        }
        return element;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM "+ tableName +" WHERE id = ?";
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
