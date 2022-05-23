package com.douzone.emailist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static final String host = "192.168.56.101";
	private static final String port = "3306";
	private static final String username = "webdb";
	private static final String password = "webdb";
	
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://"+ host +":"+port+"/bookmall?charset=utf8";
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return connection;
	}
	
	public void close(Connection connection, PreparedStatement psmt, ResultSet rs) {
		try {
			if(connection != null) {
				connection.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
