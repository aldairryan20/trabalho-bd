package com.example.demo.service;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.dao.PessoaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BdService {
    //ContaDAO contaDAO;
    PessoaDAO pessoaDAO;
    Logger logger = LogManager.getLogger(getClass());
    
    public BdService() {
    }

    public void executeQuery(String sql) {
        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            // logger.log(logger.getLevel(), "Connection stablished: "+ pstm.getConnection());
            pstm.executeUpdate();
            // logger.info(sql);
            conn.close();
        } catch(SQLException e){
            logger.error("Error at "+getClass().getName()+"\nMethod = "+methodName+"\n", e.getMessage());
        }
    }

    public String getTables(String sql) {
        String methodName = new Throwable().getStackTrace()[0].getMethodName();

        var result = new StringBuilder();
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            var statement = conn.createStatement();
            var rs = statement.executeQuery(sql);
            logger.info(sql);
            while (rs.next()) {
                var columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    result.append(rs.getString(i)).append(" ");
                }
                result.append("\n");
            }
        } catch(SQLException e) {
            logger.error("Error at "+getClass().getName()+"\nMethod = "+methodName+"\n", e.getMessage());
        }
        return result.toString();
    }
}
