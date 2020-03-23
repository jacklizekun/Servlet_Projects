<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="dbmgmt.*,java.sql.ResultSet" pageEncoding="UTF-8"%>
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

.imp {
	color: #F00;
}
</style>
</head>
<body>
	<table class="easyui-datagrid" toolbar="#toolbar"
		style="width: fit; height: fit" striped="true" loadMsg="正在加载数据…"
		singleSelect="true" rownumbers="true">
		<thead>
			<tr>
				<th colspan="8">员工基本信息</th>
				<th colspan="2">操作</th>
			</tr>
			<tr>
				<th field="员工姓名" style="width: 80px; height: fit"><div
						align="center">员工姓名</div></th>
				<th field="工号" style="width: 120px; height: fit"><div
						align="center">工号</div></th>
				<th field="性别" style="width: 80px; height: fit"><div
						align="center">性别</div></th>
				<th field="出生日期" style="width: 120px; height: fit"><div
						align="center">出生日期</div></th>
				<th field="电话" style="width: 120px; height: fit"><div
						align="center">电话</div></th>
				<th field="邮箱" style="width: 180px; height: fit"><div
						align="center">邮箱</div></th>
				<th field="住址" style="width: 80px; height: fit"><div
						align="center">住址</div></th>
				<th field="备注" style="width: 80px; height: fit"><div
						align="center">备注</div></th>
				
				<th field="修改" style="width: 80px; height: fit"></th>
				<th field="删除" style="width: 80px; height: fit"></th>

			</tr>
		</thead>

		<tbody>
			<%
				String ByName = new String(request.getParameter("ByName").trim().getBytes("iso-8859-1"), "utf-8");
				String se = new String(request.getParameter("se").trim().getBytes("iso-8859-1"), "utf-8");	
				ExecuteDB myExecuteDB = new ExecuteDB();
				String strSql = "select * from employee where "+se+" like '%" + ByName + "%'order by 工号";
				ResultSet rs = myExecuteDB.exeQuery(strSql);

				while (rs.next()) {
					String emname = rs.getString("员工姓名");
					int ennum = rs.getInt("工号");
					String emsex = rs.getString("性别");
					String embirthtday = rs.getString("出生日期");
					int emtele = rs.getInt("电话");
					String ememail = rs.getString("邮箱");
					String emaddress = rs.getString("住址");
					String remarks = rs.getString("备注");
			%>

			<tr>
				<td><div align="center"><%=emname%></div></td>
				<td><div align="center" id="codeid"><%=ennum%></div></td>
				<td><div align="center"><%=emsex%></div></td>
				<td><div align="center"><%=embirthtday%></div></td>
				<td><div align="center"><%=emtele%></div></td>
				<td><div align="center"><%=ememail%></div></td>
				<td><div align="center"><%=emaddress%></div></td>
				<td><div align="center"><%=remarks%></div></td>
				<td><div align="center">
					<a href="/courseDesignProject/employee/edit2em.jsp?emname=<%=emname%>&ennum=<%=ennum%>
					&emsex=<%=emsex%>&embirthtday=<%=embirthtday%>
				&emtele=<%=emtele%>&ememail=<%=ememail%>&emaddress=<%=emaddress%>&remarks=<%=remarks%>
				">修改</a>
					</div></td>
				<td><div align="center"><a href="/courseDesignProject/deleemservlet?ennum=<%=ennum%>">删除</a></div></td>

			</tr>



			<%
				}
			%>
		</tbody>
	</table>
	<div id="toolbar">
		<!-- #代表链接到当前页面 -->

		<a href="/courseDesignProject/basic_information/add.jsp"
			class="easyui-linkbutton" iconCls="icon-add" plain="true">添加员工信息</a>
		<!--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" >修改删除信息</a>   -->
			<label for="se">搜索范围：</label>
		<select id="se" class="easyui-combobox" name="dept" style="width:100px;" >
    		<option value="员工姓名">员工姓名</option>
  		 	<option value="工号">工号</option>
   			<option value="电话">电话</option>
   			<option value="邮箱">邮箱</option>
		</select>
		<input id="searchByName" class="easyui-textbox"
			style="line-height: 26px; border: 1px solid #ccc" prompt="查询关键字" value="<%=ByName%>">
		<a href="#" class="easyui-linkbutton" plain="true"
			iconCls="icon-search" onclick="doSearchem()">查询</a>


	</div>


<script type="text/javascript" >
$('#se').combobox({  //为下拉框赋值
    value:'<%=se%>'
  });

function doSearchem() {
	var $searchByName = $('#searchByName')
    var ByName = $searchByName.val()
    var sev=$('#se').combobox('getValue');
	//alert("/courseDesignProject/searchemservlet?ByName="+ByName+"&se="+sev);
    window.location.href="/courseDesignProject/searchemservlet?ByName="+ByName+"&se="+sev;
}
</script>


</body>
</html>