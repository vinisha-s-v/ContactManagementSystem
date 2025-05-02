package com.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnectionUtil {
	
	DBConnectionUtil(){
		logger.info("DB connection calling...");
	}
	
    private static final Logger logger = LoggerFactory.getLogger(DBConnectionUtil.class);	
	private static final String URL = "jdbc:postgresql://localhost:5432/cms";
	private static final String USER = "postgres";
    private static final String PASS = "msRashid276";
    
    public static void init() {
    	try {
    		Class.forName("org.postgresql.Driver");
    		logger.info("PostgreSQL driver loaded");
    	}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static Connection getConnection() throws SQLException {
    	return DriverManager.getConnection(URL,USER,PASS);
    }
}
