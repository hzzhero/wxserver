package com.example.util;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DriverLoader
{
  private HashMap<String, Driver> driversMap;
  private HashMap<String, File[]> defaultDriverFiles;
  private HashMap<String, String> driverClassNames;

  @SuppressWarnings("unchecked")
public DriverLoader()
  {
    this.driversMap = new HashMap();
    this.defaultDriverFiles = new HashMap();
    this.driverClassNames = new HashMap();
  }

  public synchronized void register(String dbType, String driverClassName, File[] driverFiles)
  {
    this.defaultDriverFiles.put(dbType.toLowerCase(), driverFiles);
    this.driverClassNames.put(dbType.toLowerCase(), driverClassName);
  }

  @SuppressWarnings("unchecked")
public synchronized void loadDriver(String dbType)
  {
    try
    {
      dbType = dbType.toLowerCase();
      Driver driver = (Driver)this.driversMap.get(dbType);
      if (driver != null)
      {
        DriverManager.registerDriver(driver);
      }
      else
      {
        File[] files = (File[])this.defaultDriverFiles.get(dbType);
        if (files == null)
        {
          throw new RuntimeException("driver not registered");
        }
        URL[] urls = new URL[files.length];
        for (int i = 0; i < urls.length; i++)
        {
          urls[i] = files[i].toURI().toURL();
        }

        URLClassLoader classLoader = new URLClassLoader(urls);
        Class driverClass = classLoader.loadClass((String)this.driverClassNames.get(dbType));
        driver = new DriverAdapter((Driver)driverClass.newInstance());
        DriverManager.registerDriver(driver);
        this.driversMap.put(dbType.toLowerCase(), driver);
      }
    }
    catch (Exception e)
    {
    	e.printStackTrace();
    }
  }

  public synchronized void unloadDriver(String dbType)
  {
    try
    {
      Driver driver = (Driver)this.driversMap.get(dbType.toLowerCase());
      if (driver != null)
      {
        DriverManager.deregisterDriver(driver);
      }
    }
    catch (SQLException sqle)
    {	
    	sqle.printStackTrace();
    }
  }

  public synchronized void removeDriver(String dbType) {
    Driver driver = (Driver)this.driversMap.get(dbType.toLowerCase());
    if (driver != null)
    {
      this.driversMap.remove(dbType.toLowerCase());
    }
  }
}