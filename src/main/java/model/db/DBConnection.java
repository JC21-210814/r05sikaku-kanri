package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static final String DB_SERVER = "192.168.54.238";
	static final String PORT = "3310";
	static final String DB_NAME = "certification_management";
	static final String USER = "jk3b10";
	static final String PASSWORD = "210814";
	static final String URL = "jdbc:mysql://"+DB_SERVER+":"+PORT+"/"+DB_NAME;
	
	public static Connection getConnection() {
		// jdbc.Driverクラスのロード
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
