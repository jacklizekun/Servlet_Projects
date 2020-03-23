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
	<%


String whnum = new String(request.getParameter("whnum").getBytes("ISO-8859-1"), "utf-8");
String codestr = new String(request.getParameter("code").getBytes("ISO-8859-1"), "utf-8");
String qun = new String(request.getParameter("qun").getBytes("ISO-8859-1"), "utf-8");
//request.getParameter("sportName");解决中文乱码问题
String buyprice = new String(request.getParameter("buyprice").getBytes("ISO-8859-1"), "utf-8");
String defaultloc = new String(request.getParameter("defaultloc").getBytes("ISO-8859-1"), "utf-8");
String supplier = new String(request.getParameter("supplier").getBytes("ISO-8859-1"), "utf-8");
String agentstr = new String(request.getParameter("agent").getBytes("ISO-8859-1"), "utf-8");
int agentint=Integer.parseInt(agentstr);
String remarks = new String(request.getParameter("remarks").getBytes("ISO-8859-1"), "utf-8");

System.out.println(whnum);


%>


	<form action="/courseDesignProject/editinservlet" method="post">
		<table width="60%" border="1" class="margin" cellspacing="0" style="font-size:18px;font-family:serif;">
			<tr style="background-color:#F8F9FF" >
				<td  class="ta">备件入库信息登记表（<span class="c_red">*号为必填项</span>）
				</td>
			</tr>
			<tr>
				<td  class="ta">入库单号：<%=whnum %> <span class="c_red">*</span></td>
			</tr>
			<tr>
				<td  class="ta">入库备件编码：<%=codestr %>
				<%
				ExecuteDB myExecuteDB=new ExecuteDB();
				String strSql="select 备件编码 from spare_parts";
				ResultSet rs=myExecuteDB.exeQuery(strSql);%>
				<span class="c_red">*</span>
				</td>
			</tr>
			<tr>
				<td  class="ta">数量：<input name="qun" type="text" value="<%=qun%>" /> <span class="c_red">*</span></td>
			</tr>
			<tr>
				<td  class="ta">进货价：<input name="buyprice" type="text"
					value="<%=buyprice%>" /> <span class="c_red">*</span></td>
			</tr>
			<tr>
				<td  class="ta">默认库位：<select name="defaultloc" id="defaultloc" style="width: 100px;">
				 <%
				strSql="select 仓库编号 from warehouse";
				rs=myExecuteDB.exeQuery(strSql);
				int al=0;
				String name="";
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
					
					
				
				</select><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td  class="ta">供货单位：<select name="supplier" id="supplier"
					style="width: 100px;">
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
					
					
				
				</select></td>
			</tr>
			<tr>
				<td  class="ta">经办人工号：<select name="agent" id="agent" style="width: 100px;">
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
				</select></td>
			</tr>
			<tr>
				<td class="ta">备注：<input name="remarks" type="text" value="<%=remarks%>"></td>
			</tr>
			<tr>
				<td colspan="2"  class="ta"><input name="add" type="submit" value="修改" /></td>

			</tr>
			<tr>
				<td  class="ta"><span class="c_red">备注：请输入有效数字!</span></td>
			</tr>
		</table>
		<input type="hidden" name="whnum" value="<%=whnum %>">
	</form>
	<script type="text/javascript">
	

	$('#agent').combobox({  //为下拉框赋值
        value:'<%=agentint%>' //设置默认值
      });
	$('#supplier').combobox({  //为下拉框赋值
        value:'<%=supplier%>' //设置默认值
      });
	$('#defaultloc').combobox({  //为下拉框赋值
        value:'<%=defaultloc%>' //设置默认值
      });

 
 	
 	
 </script>
</body>
</html>