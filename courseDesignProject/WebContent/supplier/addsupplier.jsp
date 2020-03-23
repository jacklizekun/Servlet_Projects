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
</head>
<body>

<form action="/courseDesignProject/addsuppliersservlet" method="post">
		<table width="60%" border="1" class="margin" cellspacing="0" style="font-size:18px;font-family:serif;">
			<tr style="background-color:#F8F9FF" >
				<td >供应商信息登记表（<span class="c_red">*号为必填项</span>）
				</td>
			</tr>
			<tr>
				<td >供应商编号：<input id="sunum" name="sunum" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td >供应商名称：<input id="suname" name="suname" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
			<tr><td>
					负责人工号：<select name="emnum" 	style="width: 100px;">
					<%
					ExecuteDB myExecuteDB=new ExecuteDB();
					String strSql="";
					ResultSet rs=myExecuteDB.exeQuery(strSql);
					strSql="select 工号 from employee";
					ResultSet rsa=myExecuteDB.exeQuery(strSql);
					int aa=0;
					int agentnum;
					int[] ba = new int[1000];
					while (rsa.next()) {
						agentnum = rsa.getInt("工号");
						ba[aa]=agentnum;
						System.out.println("aa="+aa);
						System.out.println(ba[aa]);
						aa++;
						}
					%>
						<%
						aa--;
						while(aa>=0){
						%>
							<option value="<%=ba[aa]%>"><%=ba[aa]%></option>
						<%
						aa--;
						}%>
				</select><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td >供应商电话：<input id="sutele" name="sutele" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
		
			
			<tr>
				<td >备注：<input name="remarks"  type="text" ></td>
			</tr>
			<tr>
				<td colspan="2" ><input name="add" type="submit" value="添加" /></td>
					
			</tr>
			<tr>
				<td ><span class="c_red">备注：请输入有效数字!</span></td>
			</tr>
		</table>
</form>
</body>
</html>