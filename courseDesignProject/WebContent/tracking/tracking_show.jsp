<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="dbmgmt.*,java.sql.ResultSet,java.text.DateFormat,java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
.margin {
	margin-top: 50px;
	margin-left: 200px;
}
.ta{
 text-align:left;
}

td
{
    text-align:center;
    height: 40px;
}
</style>
</head>
<body>
	<table width="60%" border="1" class="margin" cellspacing="0"
		style="font-size: 18px; font-family: serif;">
		<tr style="background-color: #F8F9FF">
			<td colspan="7" class="ta">跟踪查询结果</td>
		</tr>
		<tr style="background-color: #F8F9FF">
			<td  colspan="7" class="ta">入库信息</td>
		</tr>
		<tr style="background-color: #F8F9FF">
			<td >入库单号</td>
			<td >备件名称</td>
			<td >备件编码</td>
			<td >入库数量</td>		
			<td >入库单价</td>		
			<td >入库时间</td>
			<td >经办人工号</td>

		</tr>
		<%
			int sinventory=0;
			String num = new String(request.getParameter("num").getBytes("ISO-8859-1"), "utf-8");
			ExecuteDB myExecuteDB = new ExecuteDB();
			String strSql = "select DATE_FORMAT(入库时间,'%Y-%m-%d %H:%i:%S')as timein,备件编码,入库单号,备件名称,数量,进货价,默认库位,供货单位,经办人,备注 from inpart left join spare_parts on inpart.入库备件编码=spare_parts.备件编码 where inpart.入库单号="
					+ num;
			System.out.println(strSql);
			ResultSet rs = myExecuteDB.exeQuery(strSql);
			while (rs.next()) {
				String timestr = rs.getString("timein");
				String whnumstr = rs.getString("入库单号");
				String namestr = rs.getString("备件名称");
				String codestr = rs.getString("备件编码");
				String qunstr = rs.getString("数量");
				String buypricestr = rs.getString("进货价");
				String defaultlocstr = rs.getString("默认库位");
				String supplierstr = rs.getString("供货单位");
				String agentstr = rs.getString("经办人");
				String remarksstr = rs.getString("备注");
				//System.out.println(timestr);
				int qunin=Integer.parseInt(qunstr);
				sinventory=sinventory+qunin;
		%>

		<tr>
			<td ><%=whnumstr%></td>
			<td ><%=namestr%></td>
			<td ><%=codestr%></td>
			<td ><%=qunstr%></td>	
			<td ><%=buypricestr%></td>	
			<td ><%=timestr%></td>
			<td ><%=agentstr%></td>
		</tr>
		<%
			}
		%>
		<tr style="background-color: #F8F9FF">
			<td  style="background-color: #F8F9FF" colspan="7" class="ta">出库信息
			</td>
		</tr>
		<tr>
			<td >入库单号</td>
			<td >备件名称</td>
			<td >备件编码</td>
			<td >出库数量</td>	
			<td >出库单价</td>					
			<td >出库时间</td>
			<td >经办人工号</td>
		</tr>
		<%
			strSql = "select DATE_FORMAT(出库时间,'%Y-%m-%d %H:%i:%S')as timeex,入库单号,备件编码,出库单号,备件名称,出库单价,出库数量,经办人 from expart left join spare_parts on expart.出库备件编码=spare_parts.备件编码 where expart.入库单号="
					+ num;
			System.out.println(strSql);
			ResultSet rs2 = myExecuteDB.exeQuery(strSql);
			while (rs2.next()) {
				String whnumstr = rs2.getString("入库单号");
				String namestr = rs2.getString("备件名称");
				String codestr = rs2.getString("备件编码");
				String qunstr = rs2.getString("出库数量");
				String pricestr = rs2.getString("出库单价");
				String timestr = rs2.getString("timeex");
				String agentstr = rs2.getString("经办人");
				//System.out.println(timestr);
				int qunex=Integer.parseInt(qunstr);
				sinventory=sinventory-qunex;
		%>

		<tr>
			<td ><%=whnumstr%></td>
			<td ><%=namestr%></td>
			<td ><%=codestr%></td>
			<td ><%=qunstr%></td>	
			<td ><%=pricestr%></td>		
			<td ><%=timestr%></td>
			<td ><%=agentstr%></td>
		</tr>
		<%
			}
		%>
		<tr style="background-color: #F8F9FF">
			<td class="ta">剩余库存
			</td>
			<td colspan="2"><%=sinventory%></td>
			<td  colspan="4"></td>
		</tr>
	</table>
</body>
</html>