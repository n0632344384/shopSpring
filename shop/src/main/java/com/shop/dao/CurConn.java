package com.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CurConn {

	private static ResourceBundle bundle = ResourceBundle.getBundle("config");
	public static final String URL = bundle.getString("url");
	public static final String USER = bundle.getString("user");
	public static final String PASS = bundle.getString("pass");
	public static final String DRIVER = bundle.getString("driver");
	public static final String LOGIN = bundle.getString("login");
	public static final String LOGINPASS = bundle.getString("loginpass");
	public static final String ENTRANCE = bundle.getString("entrance");
	public static final String REGISTRATION = bundle.getString("registration");
	private static CurConn instance = new CurConn();

	public CurConn() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASS);
			// connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("\nError message: " + e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}

	private Connection createConnection(boolean autoCommit) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASS);
			connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			System.out.println("\nError message: " + e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}

	public static Connection getConnection(boolean autoCommit) {
		return instance.createConnection(autoCommit);
	}
}