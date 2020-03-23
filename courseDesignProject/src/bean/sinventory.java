package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbmgmt.ExecuteDB;

public class sinventory {

	public sinventory() {
		// TODO Auto-generated constructor stub
	}
	
	public int sin(String numstr){

		//根据入库单号返回库存数量
		//用于跟踪查询
		
		int sinventory=0;//剩余库存
		
		
		ExecuteDB myExecuteDB = new ExecuteDB();
		String strSql = "select DATE_FORMAT(入库时间,'%Y-%m-%d %H:%i:%S')as timein,备件编码,入库单号,备件名称,数量,进货价,默认库位,供货单位,经办人,备注 from inpart left join spare_parts on inpart.入库备件编码=spare_parts.备件编码 where inpart.入库单号="
				+ numstr;
		System.out.println(strSql);
		ResultSet rs = myExecuteDB.exeQuery(strSql);
		try {
			while (rs.next()) {
				String qunstr = rs.getString("数量");
			
				int qunin=Integer.parseInt(qunstr);
				sinventory=sinventory+qunin;
}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		strSql = "select DATE_FORMAT(出库时间,'%Y-%m-%d %H:%i:%S')as timeex,入库单号,备件编码,出库单号,备件名称,出库单价,出库数量,经办人 from expart left join spare_parts on expart.出库备件编码=spare_parts.备件编码 where expart.入库单号="
				+ numstr;
		System.out.println(strSql);
		ResultSet rs2 = myExecuteDB.exeQuery(strSql);
		try {
			while (rs2.next()) {
				String qunstr = rs2.getString("出库数量");
				int qunex=Integer.parseInt(qunstr);
				sinventory=sinventory-qunex;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sinventory;

	}

}
