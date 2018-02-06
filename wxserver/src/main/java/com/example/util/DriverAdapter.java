package com.example.util;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *@FileName : (DriverAdapter.java)
 *
 *@description  : TODO(������һ�仰��������������)
 *@author : HZZ
 *@version : Version No.1
 *@create : 2017-11-03 ����11:28:25
 *@modify : 2017-11-03 ����11:28:25
 *@copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
public class DriverAdapter implements Driver {

	private Driver driver;
	
	public DriverAdapter(Driver driver)
	{
		this.driver=driver;
	}
	
	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return this.driver.acceptsURL(url);
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		return this.driver.connect(url, info);
	}

	@Override
	public int getMajorVersion() {
		return this.driver.getMajorVersion();
	}

	@Override
	public int getMinorVersion() {
		return this.driver.getMinorVersion();
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		return this.driver.getPropertyInfo(url, info);
	}

	@Override
	public boolean jdbcCompliant() {
		return this.driver.jdbcCompliant();
	}
}
