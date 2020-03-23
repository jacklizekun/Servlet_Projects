package dbmgmt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExecuteDB  extends mysqlcon{

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String errMsg;

	//构造函数
	public ExecuteDB()
	{
		conn = super.getConn();
		stmt = null;
		rs = null;
		this.errMsg = super.getErrMsg();
	}

	
	
	//执行sql 执行语句，主要是执行插入、更新和删除的SQL语句
	public boolean updateSql(String strSql) throws Exception
	{
		boolean isSuc = false;
		try
		{
   			stmt=conn.createStatement();
			stmt.executeUpdate(strSql);
			stmt.close();
			isSuc = true;
		}
		catch(Exception e){
			e.printStackTrace();
			this.errMsg = this.errMsg + "<br>" +e.toString();
			//added by zqh 20180420
			throw(e);
		}
		return isSuc;
	}

	public ResultSet exeQuery(String strSql){
		try{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(strSql);
		}
		catch(Exception e){
			e.printStackTrace();
            this.errMsg = this.errMsg + "<br>" +e.toString();
			rs = null;
		}
		return rs;
	}

	@Override
	public String getErrMsg() {
		return errMsg;
	}
}
