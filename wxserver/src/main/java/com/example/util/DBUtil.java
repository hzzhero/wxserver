package com.example.util;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 *@FileName : (DBUtil.java)
 * 
 *@description : TODO(���ݿ����Ӳ��Ը�����)
 *@author : HZZ
 *@version : Version No.1
 *@create : 2017-11-03 ����10:09:20
 *@modify : 2017-11-03 ����10:09:20
 *@copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
public class DBUtil {

	public static String APP_HOME = "WEB-INF/";
	public static String path="";
	static{
		path=DBUtil.class.getResource("/").getPath();
		APP_HOME=path+"../";
	}
	
	// ��ȡ�����ַ���
	public static String getConnectionURL(int TypeID, String databaseIp,
			String databasePort, String databaseName) {
		String connectionURL;
		switch (TypeID) {
		case 1:
			connectionURL = "jdbc:oracle:thin:@" + databaseIp + ":"	+ databasePort + ":" + databaseName;
			break;
		case 2:
			connectionURL = "jdbc:derby://" + databaseIp + ":" + databasePort + "/" + databaseName + ";";
			break;
		case 3:
			connectionURL = "jdbc:dm://" + databaseIp + ":" + databasePort + "/" + databaseName;
			break;
		case 4:
			connectionURL = "jdbc:dm://" + databaseIp + ":" + databasePort;
			break;
		case 5:
			connectionURL = "jdbc:dm://" + databaseIp + ":" + databasePort + "/" + databaseName;
			break;
		case 6:
			connectionURL = "jdbc:sqlserver://" + databaseIp + ":" + databasePort + ";databasename=" + databaseName;
			break;
		case 7:
			connectionURL = "jdbc:db2://" + databaseIp + ":" + databasePort	+ "/" + databaseName;
			break;
		case 8:
			connectionURL = "jdbc:mysql://" + databaseIp + ":" + databasePort + "/" + databaseName;
			break;
		default:
			connectionURL = null;
		}
		return connectionURL;
	}
	
	public static String getConnectionURLNew(String dbType, String databaseIp,
			String databasePort, String databaseName) {
		String connectionURL;
		if("oracle".equalsIgnoreCase(dbType)){
			connectionURL = "jdbc:oracle:thin:@" + databaseIp + ":"	+ databasePort + ":" + databaseName;
		}else if("derby".equalsIgnoreCase(dbType)){
			connectionURL = "jdbc:derby://" + databaseIp + ":" + databasePort + "/" + databaseName + ";";
		}else if("dm6".equalsIgnoreCase(dbType)){
			connectionURL = "jdbc:dm://" + databaseIp + ":" + databasePort + "/" + databaseName;
		}else if("dm7".equalsIgnoreCase(dbType)){
			connectionURL = "jdbc:dm://" + databaseIp + ":" + databasePort;
		}else if("dm".equalsIgnoreCase(dbType)){
			connectionURL = "jdbc:dm://" + databaseIp + ":" + databasePort + "/" + databaseName;
		}else if("sqlserver".equalsIgnoreCase(dbType)){
			connectionURL = "jdbc:sqlserver://" + databaseIp + ":" + databasePort + ";databasename=" + databaseName;
		}else if("db2".equalsIgnoreCase(dbType)){
			connectionURL = "jdbc:db2://" + databaseIp + ":" + databasePort	+ "/" + databaseName;
		}else if("mysql".equalsIgnoreCase(dbType)){
			connectionURL = "jdbc:mysql://" + databaseIp + ":" + databasePort + "/" + databaseName;
		}else{
			connectionURL = null;
		}
		return connectionURL;
	}

	// ��ȡ���ݿ�����
	public static String getJdbcDriver(String dbType) {
		String JdbcDriver="";
		if("oracle".equalsIgnoreCase(dbType)){
			JdbcDriver = "oracle.jdbc.driver.OracleDriver";
		}else if("derby".equalsIgnoreCase(dbType)){
			JdbcDriver = "org.apache.derby.jdbc.ClientDriver";
		}else if("dm6".equalsIgnoreCase(dbType)){
			JdbcDriver = "dm.jdbc.driver.DmDriver";
		}else if("dm7".equalsIgnoreCase(dbType)){
			JdbcDriver = "dm.jdbc.driver.DmDriver";
		}else if("dm".equalsIgnoreCase(dbType)){
			JdbcDriver = "dm.jdbc.driver.DmDriver";
		}else if("sqlserver".equalsIgnoreCase(dbType)){
			JdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		}else if("db2".equalsIgnoreCase(dbType)){
			JdbcDriver = "com.ibm.db2.jcc.DB2Driver";
		}else if("mysql".equalsIgnoreCase(dbType)){
			JdbcDriver = "com.mysql.jdbc.Driver";
		}
		return JdbcDriver;
	}

	@SuppressWarnings("unchecked")
	public static String[] getProperty(String connectionURL, int dbType) {
		String[] property = connectionURL.split(":");
		List list = new ArrayList();
		switch (dbType) {
		case 3:
			list.add(property[2].substring(2));
			list.add(property[3].split("/")[0]);
			list.add(property[3].split("/")[1]);
			break;
		case 4:
			list.add(property[2].substring(2));
			list.add(property[3]);
			break;
		case 1:
			list.add(property[3].substring(1));
			list.add(property[4]);
			list.add(property[5]);
			break;
		case 6:
			list.add(property[2].substring(2));
			list.add(property[3].split(";")[0]);
			list.add(property[3].split("=")[1]);
			break;
		case 7:
			list.add(property[2].substring(2));
			list.add(property[3].split("/")[0]);
			list.add(property[3].split("/")[1]);
			break;
		case 8:
			list.add(property[2].substring(2));
			list.add(property[3].split("/")[0]);
			list.add(property[3].split("/")[1]);
			break;
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	// �������ݿ�����
	public static String pingConnection(int dbType, String databaseIp,
			String databasePort, String databaseName, String userName,
			String userPsd)  {
		String flag = null ;
		Connection conn = null;
	//	System.out.println("��ȡ�ļ�·��:"+path+"/n");
		
		File driverDir = new File(APP_HOME, "drivers");
		DriverLoader driverLoader = new DriverLoader();// ��������
		String dbTypeString = null;
		try {
			switch (dbType) {
			case 1:
				dbTypeString = "EXTERNAL_ORACLE";
				driverLoader.register(dbTypeString,	"oracle.jdbc.driver.OracleDriver", new File[] { new File(driverDir, "oracle.jar") });
				break;			
			case 2:
				dbTypeString = "INTERNAL_DERBY";
				driverLoader.register(dbTypeString, "org.apache.derby.jdbc.ClientDriver", new File[] { new File(driverDir, "derby.jar"), new File(driverDir, "derbynet.jar"), new File(driverDir, "derbyclient.jar"),	new File(driverDir, "derbyLocale_zh_CN.jar") });
				break;
			case 3:
				dbTypeString = "EXTERNAL_DM6";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm6JdbcDriver.jar") });
				break;
			case 4:
				dbTypeString = "EXTERNAL_DM7";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm7JdbcDriver.jar") });
				break;
			case 5:
				dbTypeString = "INTERNAL_DM";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm6JdbcDriver.jar") });
				break;
			case 6:
				dbTypeString = "EXTERNAL_SQLSERVER";
				driverLoader.register(dbTypeString, "com.microsoft.sqlserver.jdbc.SQLServerDriver",	new File[] { new File(driverDir, "sqljdbc4.jar") });
				break;
			case 7:
				dbTypeString = "EXTERNAL_DB2";
				driverLoader.register(dbTypeString,	"com.ibm.db2.jcc.DB2Driver", new File[] { new File(driverDir, "db2jcc.jar"), new File(driverDir, "db2jcc_license_cu.jar") });
				break;
			case 8:
				dbTypeString = "EXTERNAL_MYSQL";
				driverLoader.register(dbTypeString, "com.mysql.jdbc.Driver", new File[] { new File(driverDir, "mysql.jar") });
				break;
			}

			driverLoader.loadDriver(dbTypeString);
			String connURL = getConnectionURL(dbType, databaseIp, databasePort,	databaseName);
			conn = DriverManager.getConnection(connURL, userName, userPsd);
			if(conn!=null) flag = " ���ݿ����ӳɹ�";
		} catch (SQLException e) {
			flag = " ���ݿ�����ʧ�ܣ�"+e.getMessage()+e.getCause();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}if (dbTypeString != null) {
				driverLoader.unloadDriver(dbTypeString);
			}
		}
		return flag;
	}

	// ��ѯ��ռ�ʹ����
	public static List<Map<String,Object>> queryTableSpace(int dbType, String databaseIp,
			String databasePort, String databaseName, String userName,
			String userPsd) throws SQLException {
		Connection conn = null;
		PreparedStatement st=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		File driverDir = new File(APP_HOME, "drivers");
		DriverLoader driverLoader = new DriverLoader();// ��������
		String dbTypeString = null;
		try {
			switch (dbType) {
			case 1:
				dbTypeString = "EXTERNAL_ORACLE";
				driverLoader.register(dbTypeString, "oracle.jdbc.driver.OracleDriver",new File[] { new File(driverDir, "oracle.jar") });
				break;
			case 2:
				dbTypeString = "INTERNAL_DERBY";
				driverLoader.register(dbTypeString,	"org.apache.derby.jdbc.ClientDriver", new File[] { new File(driverDir, "derby.jar"),new File(driverDir, "derbynet.jar"),new File(driverDir, "derbyclient.jar"),	new File(driverDir, "derbyLocale_zh_CN.jar") });
				break;
			case 3:
				dbTypeString = "EXTERNAL_DM6";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver",new File[] { new File(driverDir,"Dm6JdbcDriver.jar") });
				break;
			case 4:
				dbTypeString = "EXTERNAL_DM7";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver",new File[] { new File(driverDir,"Dm7JdbcDriver.jar") });
				break;
			case 5:
				dbTypeString = "INTERNAL_DM";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver",new File[] { new File(driverDir,"Dm6JdbcDriver.jar") });
				break;
			case 6:
				dbTypeString = "EXTERNAL_SQLSERVER";
				driverLoader.register(dbTypeString,	"com.microsoft.sqlserver.jdbc.SQLServerDriver",	new File[] { new File(driverDir, "sqljdbc4.jar") });
				break;
			case 7:
				dbTypeString = "EXTERNAL_DB2";
				driverLoader.register(dbTypeString,	"com.ibm.db2.jcc.DB2Driver", new File[] { new File(driverDir, "db2jcc.jar"),new File(driverDir, "db2jcc_license_cu.jar") });
				break;
			case 8:
				dbTypeString = "EXTERNAL_MYSQL";
				driverLoader.register(dbTypeString, "com.mysql.jdbc.Driver", new File[] { new File(driverDir, "mysql.jar") });
				break;			
			}
			driverLoader.loadDriver(dbTypeString);
			String connURL = getConnectionURL(dbType, databaseIp, databasePort,databaseName);
			conn = DriverManager.getConnection(connURL, userName, userPsd);

			// ��ѯʹ����
			String sql = " SELECT UPPER(F.TABLESPACE_NAME) as tablespace_name, "
					+ " TO_CHAR(ROUND((D.TOT_GROOTTE_MB - F.TOTAL_BYTES) / D.TOT_GROOTTE_MB * 100,2),'990.99') || '%' as used_rate "
					+ " FROM (SELECT TABLESPACE_NAME,ROUND(SUM(BYTES) / (1024 * 1024), 2) TOTAL_BYTES,ROUND(MAX(BYTES) / (1024 * 1024), 2) MAX_BYTES"
					+ " FROM SYS.DBA_FREE_SPACE GROUP BY TABLESPACE_NAME) F, (SELECT DD.TABLESPACE_NAME,ROUND(SUM(DD.BYTES) / (1024 * 1024), 2) TOT_GROOTTE_MB FROM SYS.DBA_DATA_FILES DD��GROUP BY DD.TABLESPACE_NAME) D"
					+ " WHERE D.TABLESPACE_NAME = F.TABLESPACE_NAME��ORDER BY 1 ";

			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
		    rsmd=st.getMetaData();
		    //��ý��������
		    int columnCount=rsmd.getColumnCount();
		    List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
		    Map<String,Object> data=null;
		    
		    while (rs.next()) {
		    	data=new HashMap<String,Object>();
		    	for(int i=1;i<columnCount;i++)
		    	{
		    		data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd.getColumnLabel(i)));
		    	}
		    	datas.add(data);
			}
		    return datas;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs, st, conn);
			if (dbTypeString != null) {
				driverLoader.unloadDriver(dbTypeString);
			}
		}
	}

	// �ͷ�����
	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close(); // �رս����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close(); // �ر�Statement
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close(); // �ر�����
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static void main(String[] args) throws SQLException {
		// 1.
		 /*String testCon=DBUtil.pingConnection(1, "10.1.112.236", "1521","MEDB", "DMETL", "123456");
		 System.out.println("����Oracle���ݿ�����:"+testCon);*/
		// 2.
		//testCon=DBUtil.pingConnection(8, "10.1.112.74", "3306", "dmetl", "root", "123456");
		//System.out.println("����Mysql���ݿ�����:"+testCon);
		// 3.
		/*List<Map<String, Object>> datas = queryTableSpace(1, "10.1.112.236", "1521", "MEDB","DMETL", "123456");
		System.out.println("���Բ�ѯ��ռ�ʹ����: ��ѯ��СΪ:" + datas.size());
		for(Map<String,Object> m:datas)
		{
			System.out.println(m.keySet()+m.toString());
		}*/
		//jdbc:hive2://10.2.212.189:21050/;auth=noSasl
		String testCon=DBUtil.pingConnectionNew("impala",null, "jdbc:hive2://10.2.112.205:21050/;auth=noSasl",null,null);
		System.out.println("��������:"+testCon);
		
		/*String testCon=DBUtil.pingConnectionNew("hive",null, "jdbc:hive2://10.2.112.205:10000/",null,null);
		System.out.println("��������:"+testCon);*/
	}

	public static String pingConnectionNew(final String type, final String dbType, final String url,
			final String username, final String password) {
		String result = "����ʧ��";
		//�������ݿ�����
		if(type.equalsIgnoreCase("jdbc")){
			File driverDir = new File(APP_HOME, "drivers");
			DriverLoader driverLoader = new DriverLoader();// ��������
			String dbTypeString = null;
			if(dbType.equalsIgnoreCase("oracle")){
				dbTypeString = "EXTERNAL_ORACLE";
				driverLoader.register(dbTypeString,	"oracle.jdbc.driver.OracleDriver", new File[] { new File(driverDir, "oracle.jar") });
			}else if(dbType.equalsIgnoreCase("derby")){		
				dbTypeString = "INTERNAL_DERBY";
				driverLoader.register(dbTypeString, "org.apache.derby.jdbc.ClientDriver", new File[] { new File(driverDir, "derby.jar"), new File(driverDir, "derbynet.jar"), new File(driverDir, "derbyclient.jar"),	new File(driverDir, "derbyLocale_zh_CN.jar") });
			}else if(dbType.equalsIgnoreCase("dm6")){
				dbTypeString = "EXTERNAL_DM6";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm6JdbcDriver.jar") });
			}else if(dbType.equalsIgnoreCase("dm7")){
				dbTypeString = "EXTERNAL_DM7";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm7JdbcDriver.jar") });
			}else if(dbType.equalsIgnoreCase("dm")){
				dbTypeString = "INTERNAL_DM";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm6JdbcDriver.jar") });
			}else if(dbType.equalsIgnoreCase("sqlserver")){
				dbTypeString = "EXTERNAL_SQLSERVER";
				driverLoader.register(dbTypeString, "com.microsoft.sqlserver.jdbc.SQLServerDriver",	new File[] { new File(driverDir, "sqljdbc4.jar") });
			}else if(dbType.equalsIgnoreCase("db2")){
				dbTypeString = "EXTERNAL_DB2";
				driverLoader.register(dbTypeString,	"com.ibm.db2.jcc.DB2Driver", new File[] { new File(driverDir, "db2jcc.jar"), new File(driverDir, "db2jcc_license_cu.jar") });
			}else if(dbType.equalsIgnoreCase("mysql")){
				dbTypeString = "EXTERNAL_MYSQL";
				driverLoader.register(dbTypeString, "com.mysql.jdbc.Driver", new File[] { new File(driverDir, "mysql.jar") });
			}
	
			driverLoader.loadDriver(dbTypeString);
			//1000������û�����ӳɹ��򷵻�
			ExecutorService executor = Executors.newSingleThreadExecutor();   
			FutureTask<String> future = new FutureTask<String>(new Callable<String>() {//ʹ��Callable�ӿ���Ϊ�������   
				public String call() { 
					Connection conn = null;
					try{
						conn = DriverManager.getConnection(url, username, password);
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
					if(conn!=null){
						return "���ݿ����ӳɹ�";
					}
					return "���ݿ�����ʧ��";
				}
	    	});   
			executor.execute(future);   
			try {   
				result = future.get(1000, TimeUnit.MILLISECONDS); //ȡ�ý����ͬʱ���ó�ʱִ��ʱ��Ϊ1000����
			} catch (InterruptedException e) {   
				future.cancel(true);   
			} catch (ExecutionException e) {   
				future.cancel(true);   
			} catch (TimeoutException e) {   
				future.cancel(true);   
			} finally {   
			    executor.shutdown();   
			}
		}
		return result;
	}
	
	// ��ѯ��ռ�ʹ����
	public static List<Map<String,Object>> queryTableSpaceNew(String dbType, String connURL, String userName,
			String userPsd) throws SQLException {
		Connection conn = null;
		PreparedStatement st=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		File driverDir = new File(APP_HOME, "drivers");
		DriverLoader driverLoader = new DriverLoader();// ��������
		String dbTypeString = null;
		try {
			if(dbType.equalsIgnoreCase("oracle")){
				dbTypeString = "EXTERNAL_ORACLE";
				driverLoader.register(dbTypeString, "oracle.jdbc.driver.OracleDriver",new File[] { new File(driverDir, "oracle.jar") });
			}else if(dbType.equalsIgnoreCase("derby")){
				dbTypeString = "INTERNAL_DERBY";
				driverLoader.register(dbTypeString,	"org.apache.derby.jdbc.ClientDriver", new File[] { new File(driverDir, "derby.jar"),new File(driverDir, "derbynet.jar"),new File(driverDir, "derbyclient.jar"),	new File(driverDir, "derbyLocale_zh_CN.jar") });
			}else if(dbType.equalsIgnoreCase("dm6")){
				dbTypeString = "EXTERNAL_DM6";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver",new File[] { new File(driverDir,"Dm6JdbcDriver.jar") });
			}else if(dbType.equalsIgnoreCase("dm7")){
				dbTypeString = "EXTERNAL_DM7";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver",new File[] { new File(driverDir,"Dm7JdbcDriver.jar") });
			}else if(dbType.equalsIgnoreCase("dm")){
				dbTypeString = "INTERNAL_DM";
				driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver",new File[] { new File(driverDir,"Dm6JdbcDriver.jar") });
			}else if(dbType.equalsIgnoreCase("sqlserver")){
				dbTypeString = "EXTERNAL_SQLSERVER";
				driverLoader.register(dbTypeString,	"com.microsoft.sqlserver.jdbc.SQLServerDriver",	new File[] { new File(driverDir, "sqljdbc4.jar") });
			}else if(dbType.equalsIgnoreCase("db2")){
				dbTypeString = "EXTERNAL_DB2";
				driverLoader.register(dbTypeString,	"com.ibm.db2.jcc.DB2Driver", new File[] { new File(driverDir, "db2jcc.jar"),new File(driverDir, "db2jcc_license_cu.jar") });
			}else if(dbType.equalsIgnoreCase("mysql")){
				dbTypeString = "EXTERNAL_MYSQL";
				driverLoader.register(dbTypeString, "com.mysql.jdbc.Driver", new File[] { new File(driverDir, "mysql.jar") });
			}
			driverLoader.loadDriver(dbTypeString);
			conn = DriverManager.getConnection(connURL, userName, userPsd);

			// ��ѯʹ����
			String sql = "";
			if(dbType.equalsIgnoreCase("oracle")){
				sql = " SELECT UPPER(F.TABLESPACE_NAME) as TABLESPACENAME, "
						+ " TRIM(TO_CHAR(ROUND((D.TOT_GROOTTE_MB - F.TOTAL_BYTES) / D.TOT_GROOTTE_MB * 100,2),'990.99')) as USEDRATE "
						+ " FROM (SELECT TABLESPACE_NAME,ROUND(SUM(BYTES) / (1024 * 1024), 2) TOTAL_BYTES,ROUND(MAX(BYTES) / (1024 * 1024), 2) MAX_BYTES"
						+ " FROM SYS.DBA_FREE_SPACE GROUP BY TABLESPACE_NAME) F, (SELECT DD.TABLESPACE_NAME,ROUND(SUM(DD.BYTES) / (1024 * 1024), 2) TOT_GROOTTE_MB FROM SYS.DBA_DATA_FILES DD��GROUP BY DD.TABLESPACE_NAME) D"
						+ " WHERE D.TABLESPACE_NAME = F.TABLESPACE_NAME��ORDER BY 1 ";
			}else if(dbType.equalsIgnoreCase("mysql")){
				sql = " SELECT TABLE_SCHEMA as TABLESPACENAME, ROUND(IFNULL(SUM(DATA_LENGTH+INDEX_LENGTH)*100/SUM(DATA_LENGTH+INDEX_LENGTH+DATA_FREE),0),2) AS USEDRATE "
					   + " FROM INFORMATION_SCHEMA.TABLES "
				       + " WHERE TABLE_SCHEMA != 'information_schema' "
				       + " GROUP BY TABLE_SCHEMA ORDER BY 1 ";
			}//���ﻹ������������ݿ�Ĳ�ѯ���

			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
		    rsmd=st.getMetaData();
		    //��ý��������
		    int columnCount=rsmd.getColumnCount();
		    List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
		    Map<String,Object> data=null;
		    
		    while (rs.next()) {
		    	data=new HashMap<String,Object>();
		    	for(int i=1;i<=columnCount;i++)
		    	{
		    		data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd.getColumnLabel(i)));
		    	}
		    	datas.add(data);
			}
		    return datas;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs, st, conn);
			if (dbTypeString != null) {
				driverLoader.unloadDriver(dbTypeString);
			}
		}
	}
	
	//��ȡ��ļ�¼���ʹ�С
	public static Map<String,Object> getTableRowCountAndSize(final String dbType, final String url,
			final String username, final String password, final String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long rowCount = null;
		Double tableSize = null;
		File driverDir = new File(APP_HOME, "drivers");
		DriverLoader driverLoader = new DriverLoader();// ��������
		String dbTypeString = null;
		Connection conn = null;
		Statement st=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		if(dbType.equalsIgnoreCase("oracle")){
			dbTypeString = "EXTERNAL_ORACLE";
			driverLoader.register(dbTypeString,	"oracle.jdbc.driver.OracleDriver", new File[] { new File(driverDir, "oracle.jar") });
		}else if(dbType.equalsIgnoreCase("derby")){		
			dbTypeString = "INTERNAL_DERBY";
			driverLoader.register(dbTypeString, "org.apache.derby.jdbc.ClientDriver", new File[] { new File(driverDir, "derby.jar"), new File(driverDir, "derbynet.jar"), new File(driverDir, "derbyclient.jar"),	new File(driverDir, "derbyLocale_zh_CN.jar") });
		}else if(dbType.equalsIgnoreCase("dm6")){
			dbTypeString = "EXTERNAL_DM6";
			driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm6JdbcDriver.jar") });
		}else if(dbType.equalsIgnoreCase("dm7")){
			dbTypeString = "EXTERNAL_DM7";
			driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm7JdbcDriver.jar") });
		}else if(dbType.equalsIgnoreCase("dm")){
			dbTypeString = "INTERNAL_DM";
			driverLoader.register(dbTypeString, "dm.jdbc.driver.DmDriver", new File[] { new File(driverDir, "Dm6JdbcDriver.jar") });
		}else if(dbType.equalsIgnoreCase("sqlserver")){
			dbTypeString = "EXTERNAL_SQLSERVER";
			driverLoader.register(dbTypeString, "com.microsoft.sqlserver.jdbc.SQLServerDriver",	new File[] { new File(driverDir, "sqljdbc4.jar") });
		}else if(dbType.equalsIgnoreCase("db2")){
			dbTypeString = "EXTERNAL_DB2";
			driverLoader.register(dbTypeString,	"com.ibm.db2.jcc.DB2Driver", new File[] { new File(driverDir, "db2jcc.jar"), new File(driverDir, "db2jcc_license_cu.jar") });
		}else if(dbType.equalsIgnoreCase("mysql")){
			dbTypeString = "EXTERNAL_MYSQL";
			driverLoader.register(dbTypeString, "com.mysql.jdbc.Driver", new File[] { new File(driverDir, "mysql.jar") });
		}

		driverLoader.loadDriver(dbTypeString);
		try {
			conn = DriverManager.getConnection(url, username, password);
			st = conn.createStatement();
			rs = st.executeQuery("select count(*) from " + tableName);
			if(rs.next()){
				rowCount = rs.getLong(1);
			}
			
			String tableSizeSql = null;
			if(dbType.equalsIgnoreCase("oracle")){
				tableSizeSql = " SELECT round(SUM(t.bytes / 1024 / 1024),2) as tableSize "
					         + " FROM dba_segments t "
					         + " WHERE t.segment_type = 'TABLE' "
					         + " AND t.OWNER = '" + username.toUpperCase() + "' "
					         + " AND t.segment_name = '" + tableName + "' "
					         + " GROUP BY  t.segment_name ";
			}else if(dbType.equalsIgnoreCase("mysql")){
				tableSizeSql = " SELECT ROUND(SUM(DATA_LENGTH+INDEX_LENGTH) / 1024 / 1024,2) AS tableSize "
					         + " FROM INFORMATION_SCHEMA.TABLES "
				             + " WHERE TABLE_NAME = '" + tableName + "' "
				             + " GROUP BY TABLE_NAME ";
			}
			if(tableSizeSql!=null){
				rs1 = st.executeQuery(tableSizeSql);
				if(rs1.next()){
					tableSize = rs1.getDouble(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			free(rs, st, conn);
			free(rs1, st, conn);
			if (dbTypeString != null) {
				driverLoader.unloadDriver(dbTypeString);
			}
		}
		map.put("rowCount", rowCount);
		map.put("tableSize", tableSize);
		return map;
	}
}
