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

<form action="/courseDesignProject/addemservlet" method="post">
		<table width="60%" border="1" class="margin" cellspacing="0" style="font-size:18px;font-family:serif;">
			<tr style="background-color:#F8F9FF" >
				<td class="ta">员工信息登记表（<span class="c_red">*号为必填项</span>）
				</td>
			</tr>
			<tr>
				<td class="ta">姓名：
				<input id="emname" name="emname" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">工号：<input id="ennum" name="ennum" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span>
				</td>
			</tr>
			<tr>
				<td class="ta">性别：<select name="emsex" style="width: 100px;">
						<option value="男">男</option>
						<option value="女">女</option>
				</select>
				<span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">出生日期：<input class="easyui-datebox" name="embirthtday" 
				id="embirthtday" style="width:180px;line-height: 26px; border: 1px solid #ccc" required="true" missingMessage="该项为必填项">
				<span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">电话：<input id="emtele" name="emtele" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">邮箱：<input id="ememail" name="ememail" class="easyui-validatebox tb" data-options="required:true,validType:'email'">
				<span class="c_red">*</span></td>
			</tr>
			<tr>
				<td class="ta">住址：<input id="emaddress" name="emaddress" type="text" class="easyui-validatebox" /></td>
			</tr>
			<tr>
				<td class="ta">备注：<input id="remarks" name="remarks" type="text" class="easyui-validatebox" /></td>
			</tr>
			<tr>
				<td colspan="2" class="ta"><input name="add" type="submit" value="添加" /></td>
					
			</tr>
			<tr>
				<td class="ta"><span class="c_red">备注：请输入有效信息!</span></td>
			</tr>
		</table>
</form>

 
</body>
</html> 