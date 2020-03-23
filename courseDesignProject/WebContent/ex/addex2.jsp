<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dbmgmt.*,java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<style type="text/css">
.c_red {
	color: #F00;
}

.margin {
	margin-top: 50px;
	margin-left: 200px;
}
td{
	height:40px;
}
</style>
<script type="text/javascript" src="/courseDesignProject/jquery/jquery-3.4.1.js"></script>
</head>
<body>
<%
	
	String name =new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
	String code =new String(request.getParameter("code").getBytes("ISO-8859-1"),"utf-8");
	System.out.println("name:"+name);
	System.out.println("code:"+code);
%>
<form action="/courseDesignProject/addexservlet2" method="post">
		<table width="60%" border="1" class="margin" cellspacing="0" style="font-size:18px;font-family:serif;">
			<tr style="background-color:#F8F9FF" >
				<td >备件出库信息登记表（<span class="c_red">*号为必填项</span>）
				</td>
			</tr>
			<tr>
				<td >备件名称：<%=name %><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td >备件编号：<%=code %><span class="c_red">*</span></td>
			</tr>
			<tr>
			<td >入库单号：<select name="num" style="width: 120px;">
					<%
					String strSql="select 入库单号 from inpart where 入库备件编码='"+code+"'";
					ExecuteDB myExecuteDB=new ExecuteDB();
					ResultSet rs=myExecuteDB.exeQuery(strSql);
					System.out.println(strSql);
					int a=0;
					String num;
					String[] b = new String[1000];
					while (rs.next()) {
						num = rs.getString("入库单号");
						b[a]=num;
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
				</select><span class="c_red">*</span></td>
			</tr>
			
			<tr>
				<td colspan="2" ><input name="add" type="submit" value="下一步" /></td>
					
			</tr>
			<tr>
				<td><span class="c_red">备注：请输入有效数字!</span></td>
			</tr>
		</table>
		<input type="hidden" name="name" value="<%=name%>">
		<input type="hidden" name="code" value="<%=code%>">
</form>

 
</body>
</html> 