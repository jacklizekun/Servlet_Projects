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
				<th colspan="13">物料基本信息</th>
			</tr>
			<tr>
				<th field="备件名称" style="width: 80px; height: fit"><div
						align="center">备件名称</div></th>
				<th field="备件编码" style="width: 120px; height: fit"><div
						align="center">备件编码</div></th>
				<th field="备件剩余库存" style="width: 120px; height: fit"><div
						align="center">备件剩余库存</div></th>		
				<th field="备件单位" style="width: 80px; height: fit"><div
						align="center">备件单位</div></th>
				<th field="存放库区" style="width: 80px; height: fit"><div
						align="center">存放库区</div></th>
				<th field="备件单价" style="width: 80px; height: fit"><div
						align="center">备件单价</div></th>
				<th field="备件质量" style="width: 80px; height: fit"><div
						align="center">备件质量</div></th>
				<th field="备件规格" style="width: 80px; height: fit"><div
						align="center">备件规格</div></th>
				<th field="安全库存" style="width: 80px; height: fit"><div
						align="center">安全库存</div></th>
				<th field="原始库存" style="width: 80px; height: fit"><div
						align="center">原始库存</div></th>
				<th field="初始年需求量" style="width: 100px; height: fit"><div
						align="center">初始年需求量</div></th>
				<th field="订货成本" style="width: 80px; height: fit"><div
						align="center">订货成本</div></th>
				<th field="单位库存成本" style="width: 100px; height: fit"><div
						align="center">单位库存成本</div></th>
				<th field="修改" style="width: 80px; height: fit"></th>
				<th field="删除" style="width: 80px; height: fit"></th>

			</tr>
		</thead>

		<tbody>
			<%
	
				ExecuteDB myExecuteDB = new ExecuteDB();
				String strSql = "select* from inventory left join spare_parts on inventory.库存备件编码=spare_parts.备件编码 order by 备件编码";
				ResultSet rs = myExecuteDB.exeQuery(strSql);

				while (rs.next()) {
					String name = rs.getString("备件名称");
					String code = rs.getString("备件编码");
					String sin = rs.getString("剩余库存");
					String unit = rs.getString("备件单位");
					String place = rs.getString("存放库区");
					String unitprice = rs.getString("备件单价");
					String quntity = rs.getString("备件质量");
					String stan = rs.getString("备件规格");
					String safestoke = rs.getString("安全库存");
					String pristoke = rs.getString("原始库存");
					String pridemand = rs.getString("初始年需求量");
					String ordercost = rs.getString("订货成本");
					String unitpprice = rs.getString("单位库存成本");
			%>

			<tr>
				<td><div align="center"><%=name%></div></td>
				<td><div align="center" id="codeid"><%=code%></div></td>
				<td><div align="center" id="codeid"><%=sin%></div></td>
				<td><div align="center"><%=unit%></div></td>
				<td><div align="center"><%=place%></div></td>
				<td><div align="center"><%=unitprice%></div></td>
				<td><div align="center"><%=quntity%></div></td>
				<td><div align="center"><%=stan%></div></td>
				<td><div align="center"><%=safestoke%></div></td>
				<td><div align="center"><%=pristoke%></div></td>
				<td><div align="center"><%=pridemand%></div></td>
				<td><div align="center"><%=ordercost%></div></td>
				<td><div align="center"><%=unitpprice%></div></td>
				<td><div align="center">
			</tr>
	


			<%
				}
			%>
		</tbody>
	</table>
	<div id="toolbar">	
		<label for="se">搜索范围：</label>
		<select id="se" class="easyui-combobox" name="se" style="width:100px;">
    		<option value="备件名称">备件名称</option>
  		 	<option value="备件编码">备件编码</option>
		</select>
		<input id="searchByName" name="searchByName" class="easyui-textbox"
			style="line-height: 26px; border: 1px solid #ccc" prompt="查询关键字">
		<a href="#" class="easyui-linkbutton" plain="true"
			iconCls="icon-search" onclick="doSearchinventory()">查询</a>

	</div>
<script type="text/javascript">
function doSearchinventory() {
    var $ByName = $('#searchByName');
    var ByName = $ByName.val();
    var $se = $('#se');
    var se = $se.val();
    //alert(start);
   //alert("/courseDesignProject/searchservlt?ByName="+ByName);
    window.location.href="/courseDesignProject/invenservlet?ByName="+ByName+"&se="+se;
}
</script>
</body>
</html>