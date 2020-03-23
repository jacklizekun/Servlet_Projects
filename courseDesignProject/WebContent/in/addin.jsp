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
.ta{
	height:40px;
}
</style>

</head>
<body>

<form action="/courseDesignProject/addinservlet" method="post">
		<table width="60%" border="1" class="margin" cellspacing="0" style="font-size:18px;font-family:serif;">
			<tr style="background-color:#F8F9FF" >
				<td class="ta">备件入库信息登记表（<span class="c_red">*号为必填项</span>）
				</td>
			</tr>
			<tr>
				<td class="ta">入库单号：<input id="whnum" name="whnum" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">入库备件编码：
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
			<tr>
				<td class="ta">数量：<input id="qun" name="qun" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">进货价：<input id="buyprice" name="buyprice" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">默认库位：
				<select name="defaultloc" style="width: 100px;">
				 <%
				strSql="select 仓库编号 from warehouse";
				rs=myExecuteDB.exeQuery(strSql);
				int al=0;
				name="";
				String[] bl = new String[1000];
				while (rs.next()) {
					name = rs.getString("仓库编号");
					bl[al]=name;
					System.out.println("al="+al);
					System.out.println(bl[al]);
					al++;
					
					}
				%>
					<%
					al--;
					while(al>=0){
					%>
						<option value="<%=bl[al]%>"><%=bl[al]%></option>
					<%
					al--;
					}%>
					
					
				
				</select>
				<span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">供货单位：<select name="supplier" style="width: 100px;">
				 <%
				strSql="select 供应商名称 from supplier";
				rs=myExecuteDB.exeQuery(strSql);
				int ab=0;
				name="";
				String[] bb = new String[1000];
				while (rs.next()) {
					name = rs.getString("供应商名称");
					bb[ab]=name;
					System.out.println("ab="+ab);
					System.out.println(bb[ab]);
					ab++;
					
					}
				%>
					<%
					ab--;
					while(ab>=0){
					%>
						<option value="<%=bb[ab]%>"><%=bb[ab]%></option>
					<%
					ab--;
					}%>
					
					
				
				</select><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">经办人工号：<select name="agent" 	style="width: 100px;">
					<%
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
				<td class="ta">备注：<input name="remarks"  type="text" ></td>
			</tr>
			<tr>
				<td colspan="2" class="ta"><input name="add" type="submit" value="添加" /></td>
					
			</tr>
			<tr>
				<td class="ta"><span class="c_red">备注：请输入有效数字!</span></td>
			</tr>
		</table>
</form>

 
</body>
</html> 