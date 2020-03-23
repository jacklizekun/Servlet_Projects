package dbmgmt;

import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlcon {
	
	// 数据库用户名
	String dbuserName = "root";
	// 数据库密码
	String dbuserPassword = "";

	String DBDRIVER = "com.mysql.jdbc.Driver"; // if you use mysql
	String DBURL = "jdbc:mysql://localhost:3306/material_storage";// if you use mysql

	// 数据库的URL，包括连接数据库所使用的编码格式
	//String url = "jdbc:mysql://localhost:3306/material_storage?useUnicode=true&characterEncoding=UTF-8";
	String url = "jdbc:mysql://localhost:3306/material_storage?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true";
	// 定义一个连接对象
	Connection dbConn;
	
	// 错误信息串
	String errMsg;

	public mysqlcon() {
		// 初始化操作
		errMsg = "";
		dbConn = null;
	}

	// 连接数据库;
	public Connection getConn() {
		try {
			// 声明所用的类包
			Class.forName(DBDRIVER);
			// 获得数据库的连接对象
			dbConn = DriverManager.getConnection(url, dbuserName, dbuserPassword);
			// System.out.println(dbConn);
		} catch (Exception e) {
			dbConn = null;
			e.printStackTrace();
			errMsg = e.toString();
		}
		return dbConn;
	}

	// 获取错误信息
	public String getErrMsg() {
		return errMsg;
	}
}
