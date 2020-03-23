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
}</style>
</head>
<body>
	<table class="easyui-datagrid" toolbar="#toolbar"
		style="width: fit; height: fit" striped="true" loadMsg="正在加载数据…" singleSelect="true" rownumbers="true">
		<thead>
			<tr>
				<th colspan="6">入库信息</th>
				<th colspan="2">操作</th>
			</tr>
			<tr>
				<th field="仓库编号" style="width: 120px; height: fit"><div align="center">仓库编号</div></th>
				<th field="仓库负责人工号" style="width: 120px; height: fit"><div align="center">仓库负责人工号</div></th>
				<th field="负责人姓名" style="width: 120px; height: fit"><div align="center">负责人姓名</div></th>
				<th field="负责人电话" style="width: 120px; height: fit"><div align="center">负责人电话</div></th>
				<th field="仓库电话" style="width: 120px; height: fit"><div align="center">仓库电话</div></th>
				<th field="备注" style="width: 120px; height: fit"><div align="center">备注</div></th>
				<th field="修改" style="width: 80px; height: fit"></th>
				<th field="删除" style="width: 80px; height: fit"></th>
				
			</tr>
		</thead>

		<tbody>
			<%
			String ByName =new String(request.getParameter("ByName").trim().getBytes("iso-8859-1"),"utf-8");
			String se =new String(request.getParameter("se").trim().getBytes("iso-8859-1"),"utf-8");
		
				ExecuteDB myExecuteDB = new ExecuteDB();
				String strSql = "select 仓库编号,仓库负责人工号,员工姓名,电话,仓库电话,仓库备注 from warehouse left join employee on warehouse.仓库负责人工号=employee.工号 where "+se+" like '%" + ByName + "%' order by 工号";
				System.out.println(strSql);
				ResultSet rs = myExecuteDB.exeQuery(strSql);
				while (rs.next()) {
					
					String warnum = rs.getString("仓库编号");
					String emnum = rs.getString("仓库负责人工号");
					String enname = rs.getString("员工姓名");
					String entele = rs.getString("电话");
					String tele = rs.getString("仓库电话");
					String remarks = rs.getString("仓库备注");
					//System.out.println(timestr);
				
					
			%>
			
			<tr>
				<td><div align="center"><%=warnum%></div></td>
				<td><div align="center"><%=emnum%></div></td>
				<td><div align="center"><%=enname%></div></td>
				<td><div align="center"><%=entele%></div></td>
				<td><div align="center"><%=tele%></div></td>
				<td><div align="center"><%=remarks%></div></td>
				
				<td><div align="center"><a href="/courseDesignProject/warehouse/editwarehouse2.jsp?warnum=<%=warnum%>
				&emnum=<%=emnum%>&tele=<%=tele%>&remarks=<%=remarks%>
				">修改</a></div></td>
				<td><div align="center"><a href="/courseDesignProject/delewarehouseservlet?warnum=<%=warnum%>">删除</a></div></td>
	
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	
	<div id="toolbar">
		<!-- #代表链接到当前页面 -->
			<a href="/courseDesignProject/warehouse/addwarehouse.jsp" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			>添加仓库信息</a> 
			<label for="se">搜索范围：</label>
		<select id="se" class="easyui-combobox" name="se" style="width:100px;">
    		<option value="仓库编号">仓库编号</option>
  		 	<option value="仓库负责人工号">仓库负责人工号</option>
		</select>
		<input id="searchByName" class="easyui-textbox"
			style="line-height: 26px; border: 1px solid #ccc" prompt="查询关键字">			
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearchwarehouse()">查询</a>
	</div>
	
	<script type="text/javascript" >
	$('#se').combobox({  //为下拉框赋值
	    value:'<%=se%>'
	  });
	$('#searchByName').val('<%=ByName%>');

	function doSearchwarehouse() {
	    var $ByName = $('#searchByName');
	    var ByName = $ByName.val();
	    var $se = $('#se');
	    var se = $se.val();
	    //alert(start);
	   //alert("/courseDesignProject/searchservlt?ByName="+ByName);
	    window.location.href="/courseDesignProject/searchwarehouseservlt?ByName="+ByName+"&se="+se;
	}</script>
</body>
</html>