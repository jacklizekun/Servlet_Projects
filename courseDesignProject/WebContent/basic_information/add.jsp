<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加物料信息</title>

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
.imp {
	color: #F00;
}
.margin{
	margin-top:50px;
	margin-left:150px;
	
}
.ta{
	height:40px;
}
</style>
</head>

<body>



<form name="myform" action="/courseDesignProject/addservlet" method="post"> 

		<table width="70%" border="1" class="margin" cellspacing="0" style="font-size:18px;font-family:serif;">
			<tr style="background-color:#F8F9FF" >
				<td colspan="2" class="ta">备件信息添加（<span class="imp">*为必填项</span>）
				</td>

			</tr>
			<tr>
				<td class="ta">备件名称<input id="name" name="name" type="text" class="easyui-validatebox" 
				required="true" missingMessage="该项为必填项"/><span class="imp">*</span></td>
				<td class="ta">备件编码<input name="code" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span class="imp">*</span></td>
			</tr>
			<tr>
				<td class="ta">备件单位<input name="unit" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span class="imp">*</span></td>
				<td class="ta">存放库区<select name="place">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
				</select></td>
			</tr>
			<tr>
				<td class="ta">备件单价<input name="unitprice" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span class="imp">*元</span></td>
				<td class="ta">备件质量<input name="quntity" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span class="imp">*千克</span></td>
			</tr>
			<tr>
				<td class="ta">备件规格<input name="stan" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span class="imp">*</span></td>
				<td class="ta">安全库存<input name="safestoke" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span
					class="imp">*</span></td>
			</tr>
			<tr>
				<td class="ta">原始库存<input name="pristoke" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span class="imp">*</span></td>
				<td class="ta">初始年需求量<input name="pridemand" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span
					class="imp">*</span></td>
			</tr>
			<tr>
				<td class="ta">订货成本<input name="ordercost" type="text" class="easyui-validatebox"  required="true" missingMessage="该项为必填项"/><span
					class="imp">*元</span></td>
				<td class="ta">单位库存成本<input name="unitpprice" type="text" class="easyui-validatebox" required="true" missingMessage="该项为必填项"/><span
					class="imp">*元</span></td>
			</tr>
			<tr>
				<td colspan="2" class="ta">
				
				<input name="add" type="submit" value="添加" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="ta"><span class="imp" >备注：请输入有效数字!</span></td>
			</tr>
		</table>
	</form>
	
	
</body>
</html>