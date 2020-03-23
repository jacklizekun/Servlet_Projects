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
<style type="text/css">
.margin {
	margin-top: 50px;
	margin-left: 200px;
}
.ta{
	height:40px;
}
</style>
</head>
<body>
<form action="/courseDesignProject/trackingservlet" method="post">
<table width="60%" border="1" class="margin" cellspacing="0" style="font-size:18px;font-family:serif;">
	<tr style="background-color: #F8F9FF " >
		<td class="ta">库存管理查询
		</td>
	</tr>
	<tr>
		<td class="ta">
		入库单号：
		<select id="num" name="num" style="width: 120px;">
				 <%
				ExecuteDB myExecuteDB=new ExecuteDB();
				String strSql="select 入库单号 from inpart";
				ResultSet rs=myExecuteDB.exeQuery(strSql);
				int a=0;
				String name="";
				String[] b = new String[1000];
				while (rs.next()) {
					name = rs.getString("入库单号");
					b[a]=name;
					System.out.println("a="+a);
					System.out.println(b[a]);
					a++;
					
					}
				%>
					<%
					a--;
					while(a>=0){
					%>
						<option value="<%=b[a]%>"><%=b[a]%></option>
					<%
					a--;
					}%>
						
				</select>
		</td>
	</tr>
	<tr>
	<td class="ta"><input type="submit"  value="跟踪查询">
		</td>
	</tr>
</table>
</form>


</body>
</html>