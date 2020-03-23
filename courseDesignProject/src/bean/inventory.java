package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbmgmt.ExecuteDB;

public class inventory {
	ExecuteDB myExecuteDB=new ExecuteDB();
	public inventory() {
		// TODO Auto-generated constructor stub
	}
	
public void isexist(String codestr){//剩余库存表是否存在备件编码，如果没有就插入一个
	String strSql="select 剩余库存 from inventory where 库存备件编码="+codestr;
	System.out.println(strSql);
	boolean isexist=false;
	try {
		ResultSet rs=myExecuteDB.exeQuery(strSql);
		String sin="";
		while(rs.next()){
			sin = rs.getString("剩余库存");
		}
		if(sin.length()==0){
			isexist=false;
		}else{
			isexist=true;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(isexist){
		return;
	}else{
		strSql="insert into inventory (库存备件编码,剩余库存) values ('"+codestr+"','0')";
		try {
			myExecuteDB.updateSql(strSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
	


public int inven(String codestr){//根据备件编码获取剩余库存
	String strSql="select 剩余库存 from inventory where 库存备件编码="+codestr;
	System.out.println(strSql);
	ResultSet rs = myExecuteDB.exeQuery(strSql);
	int sin=0;
	try {
		while(rs.next()){sin = rs.getInt("剩余库存");}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sin;
}



public boolean inven(String code,int sin){//入库操作后，根据备件编号更新剩余库存表
	boolean issuccess=true;
	
	String strSql="update inventory set 剩余库存='"+sin+"' where 库存备件编码="+code;
	System.out.println(strSql);
	try {
		issuccess= myExecuteDB.updateSql(strSql);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return issuccess;
	
}
}
