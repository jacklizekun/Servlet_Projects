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
<%
	
	String name =new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
	String code =new String(request.getParameter("code").getBytes("ISO-8859-1"),"utf-8");
	String num =new String(request.getParameter("num").getBytes("ISO-8859-1"),"utf-8");
	String sinventory =new String(request.getParameter("sinventory").getBytes("ISO-8859-1"),"utf-8");
	String place =new String(request.getParameter("place").getBytes("ISO-8859-1"),"utf-8");
	System.out.println("sinventory:"+sinventory);
%>
<form name="exform" action="/courseDesignProject/addexservlet3" method="post">
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
				<td >入库单号：<%=num %><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td >库位：<%=place %><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td >剩余库存：<%=sinventory %></td>
			</tr>
			<tr>
				<td>出库单号：<input id="exnum" name="exnum" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/></td>
			</tr>
			<tr>
				<td>出库数量：<input id="exqun" name="exqun" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td>出库单价：<input id="exprice" name="exprice" type="text" class="easyui-validatebox" 
				/></td>
			</tr>
			<tr>
				<td >经办人工号：<select name="agent" style="width: 100px;">
					<%
					ExecuteDB myExecuteDB=new ExecuteDB();
					String strSql="select 工号 from employee";
					int aa=0;
					int agentnum;
					int[] ba = new int[1000];
					ResultSet rsa=myExecuteDB.exeQuery(strSql);
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
				<td>备注：<input type="text" name="remarks"></td>
			</tr>
			<tr>
				<td colspan="2" ><input name="ex" type="button" value="确认出库" onclick="check()"/></td>
					
			</tr>
			<tr>
				<td><span class="c_red">备注：请输入有效数字!</span></td>
			</tr>
		</table>
		<input type="hidden" name="name" value="<%=name%>">
		<input type="hidden" name="code" value="<%=code%>">
		<input type="hidden" name="num" value="<%=num%>">
		<input type="hidden" name="place" value="<%=place%>">
</form>
<script type="text/javascript">
function check(){
	var $qun = $('#exqun')
	var exportqun = $qun.val()
	if(exportqun><%=sinventory %>){
		alert("库存不足");
	}
	else{
		document.exform.submit();  
	}
}
</script>
 
</body>
</html> 