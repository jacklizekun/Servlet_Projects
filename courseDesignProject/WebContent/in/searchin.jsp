<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dbmgmt.*,java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/courseDesignProject/jquery-easyui-1.8.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="/courseDesignProject/jquery-easyui-1.8.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="/courseDesignProject/jquery-easyui-1.8.1/demo/demo.css">
<script type="text/javascript"
	src="/courseDesignProject/jquery-easyui-1.8.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/courseDesignProject/jquery-easyui-1.8.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="/courseDesignProject/jquery-easyui-1.8.1/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
a:link {
	color: #42413C;
	text-decoration: underline;
	/* 除非将链接设置成极为独特的外观样式，否则最好提供下划线，以便可从视觉上快速识别 */
}

a:visited {
	color: #6E6C64;
	text-decoration: underline;
}

a:hover, a:active, a:focus { /* 此组选择器将为键盘导航者提供与鼠标使用者相同的悬停体验。 */
	text-decoration: none;
}
</style>
</head>
<body>
	<table class="easyui-datagrid" toolbar="#toolbar"
		style="width: fit; height: fit" striped="true" loadMsg="正在加载数据…"
		singleSelect="true" rownumbers="true">
		<thead>
			<tr>
				<th colspan="9">入库信息</th>
				<th colspan="2">操作</th>
			</tr>
			<tr>
				<th field="入库时间" style="width: 200px; height: fit"><div
						align="center">入库时间</div></th>
				<th field="入库单号" style="width: 120px; height: fit"><div
						align="center">入库单号</div></th>
				<th field="备件名称" style="width: 120px; height: fit"><div
						align="center">备件名称</div></th>
						<th field="备件编码" style="width: 120px; height: fit"><div align="center">备件编码</div></th>
				<th field="数量" style="width: 80px; height: fit"><div
						align="center">数量</div></th>
				<th field="进货价" style="width: 80px; height: fit"><div
						align="center">进货价</div></th>
				<th field="默认库位" style="width: 80px; height: fit"><div
						align="center">默认库位</div></th>
				<th field="供货单位" style="width: 80px; height: fit"><div
						align="center">供货单位</div></th>
				<th field="经办人" style="width: 80px; height: fit"><div
						align="center">经办人</div></th>
				<th field="备注" style="width: 80px; height: fit"><div
						align="center">备注</div></th>
				<th field="修改" style="width: 80px; height: fit"></th>
				<th field="删除" style="width: 80px; height: fit"></th>

			</tr>
		</thead>

		<tbody>
			<%
				String starttimestr = "";
				String endtimestr = "";
				starttimestr = request.getParameter("starttime");
				endtimestr = request.getParameter("endtime");


				if (starttimestr !=null & endtimestr !=null) {
					String strSql1 = "select DATE_FORMAT(入库时间,'%Y-%m-%d %H:%i:%S')as timein,备件编码,入库单号,备件名称,数量,进货价,默认库位,供货单位,经办人,备注  from inpart left join spare_parts on inpart.入库备件编码=spare_parts.备件编码 where 入库时间 between '" + starttimestr + "' and '" + endtimestr+ "'order by timein desc";
					System.out.println(strSql1);
					ExecuteDB myExecuteDB=new ExecuteDB();
					ResultSet rs1 = myExecuteDB.exeQuery(strSql1);
					while (rs1.next()) {
						String timestr = rs1.getString("timein");
						String whnumstr = rs1.getString("入库单号");
						String namestr = rs1.getString("备件名称");
						String codestr = rs1.getString("备件编码");
						String qunstr = rs1.getString("数量");
						String buypricestr = rs1.getString("进货价");
						String defaultlocstr = rs1.getString("默认库位");
						String supplierstr = rs1.getString("供货单位");
						String agentstr = rs1.getString("经办人");
						String remarksstr = rs1.getString("备注");
			%>

			<tr>
				<td><div align="center"><%=timestr%></div></td>
				<td><div align="center"><%=whnumstr%></div></td>
				<td><div align="center"><%=namestr%></div></td>
				<td><div align="center"><%=codestr%></div></td>
				<td><div align="center"><%=qunstr%></div></td>
				<td><div align="center"><%=buypricestr%></div></td>
				<td><div align="center"><%=defaultlocstr%></div></td>
				<td><div align="center"><%=supplierstr%></div></td>
				<td><div align="center"><%=agentstr%></div></td>
				<td><div align="center"><%=remarksstr%></div></td>

				<td><div align="center">
						<a
							href="/courseDesignProject/in/editin2.jsp?whnum=<%=whnumstr%>
				&name=<%=namestr%>&qun=<%=qunstr%>&buyprice=<%=buypricestr%>
				&defaultloc=<%=defaultlocstr%>&supplier=<%=supplierstr%>&agent=<%=agentstr%>&remarks=<%=remarksstr%>
				">修改</a>
					</div></td>
				<td><div align="center">
						<a href="/courseDesignProject/deletinservlet?whnum=<%=whnumstr%>">删除</a>
					</div></td>

			</tr>
			<%
				}

				}
			%>
		</tbody>
	</table>

	<div id="toolbar">
		<!-- #代表链接到当前页面 -->
		<a href="/courseDesignProject/in/addin.jsp" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">添加入库信息</a>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改删除信息</a>   -->
		<input class="easyui-datetimebox" id="starttime"
			style="width:180px;line-height: 26px; border: 1px solid #ccc" prompt="搜索起始时间" value="<%=starttimestr%>">
		至 <input class="easyui-datetimebox" id="endtime"
			style="width:180px;line-height: 26px; border: 1px solid #ccc" prompt="搜索截止时间" value="<%=endtimestr%>">
		<a href="#" class="easyui-linkbutton" plain="true"
			iconCls="icon-search" onclick="doSearchin()">时间查询</a>
	</div>

	
	<script type="text/javascript" src="/courseDesignProject/js/search.js"></script>
</body>
</html>