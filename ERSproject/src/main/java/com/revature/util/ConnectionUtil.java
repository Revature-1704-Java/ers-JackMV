package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException, IOException {
		if (connection == null) {
			Properties prop = new Properties();
			InputStream in = new FileInputStream("connection.properties");
			prop.load(in);
			
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pass = prop.getProperty("password");
			
			return DriverManager.getConnection(url, user, pass);
	
		} else {
			return connection;
		}
	}
}
