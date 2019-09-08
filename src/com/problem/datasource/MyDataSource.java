package com.problem.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataSource  extends MyDataSourceAdapter{
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/problems?useSSL=true&serverTimezone=GMT%2B8", "root", "123456");
		return conn;
	}
}
