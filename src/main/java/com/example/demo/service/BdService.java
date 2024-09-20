package com.example.demo.service;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.dao.PessoaDAO;
import com.example.demo.dao.ContaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BdService {
    ContaDAO contaDAO;
    PessoaDAO pessoaDAO;
    Logger logger = LogManager.getLogger(getClass());

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

    
}
