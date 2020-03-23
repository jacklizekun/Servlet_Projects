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

td
{
    text-align:center;
    height: 40px;
}
</style>
</head>
<body>
	<form action="/courseDesignProject/addexservlet" method="post">
		<table width="60%" border="1" class="margin" cellspacing="0"
			style="font-size: 18px; font-family: serif;">
			<tr style="background-color: #F8F9FF">
				<td class="ta" colspan="2">备件出库信息登记表（<span class="c_red">*号为必填项</span>）
				</td>
			</tr>
			<tr>
			<td>备件编码</td>
			<td>
			<select name="code" style="width: 100px;">
			 <%
				ExecuteDB myExecuteDB=new ExecuteDB();
				String strSql="select 备件编码 from spare_parts";
				ResultSet rs=myExecuteDB.exeQuery(strSql);
				int a=0;
				String name="";
				String[] b = new String[1000];
				while (rs.next()) {
					name = rs.getString("备件编码");
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
			</select><span class="c_red">*</span>	
			</td>
			</tr>
			<tr><td colspan="2"><input value="下一步" type="submit"></td></tr>
		</table>
	</form>
</body>
</html>