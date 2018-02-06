package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectUtil {
	private  String   driverClass;
	private  String   url;
	private  String   username;
	private  String   password;
	private  Connection  conn;
	private  Statement statement; 
	
	public ConnectUtil(){}
	
	public ConnectUtil(String driverClass, String url, String username,
			String password) {
		super();
		this.driverClass = driverClass;
		this.url = url;
		this.username = username;
		this.password = password;
	}



	public void initConnection(String driverClass,String url,
			String username,String password) throws ClassNotFoundException, SQLException{
		Class.forName(driverClass);
		this.conn=DriverManager.getConnection(url, username, password);
		this.statement = this.conn.createStatement();
	}
	
	public void closeConnection() {
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	
}
