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
</head>
<body onload="func1();">
	<table class="easyui-datagrid" toolbar="#toolbar"
		style="width: fit; height: fit" striped="true" loadMsg="正在加载数据…"
		singleSelect="true" rownumbers="true">
		<thead>
			<tr>
				<th colspan="12">物料基本信息</th>
			</tr>
			<tr>
				<th field="备件名称" style="width: 80px; height: fit"><div
						align="center">备件名称</div></th>
				<th field="备件编码" style="width: 120px; height: fit"><div
						align="center">备件编码</div></th>
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
			</tr>
		</thead>

		<tbody>
			<%
				String ByName = new String(request.getParameter("ByName").trim().getBytes("iso-8859-1"), "utf-8");
				String se = new String(request.getParameter("se").trim().getBytes("iso-8859-1"), "utf-8");	
			
				//String ByName = request.getParameter("ByName").trim();
				ExecuteDB myExecuteDB = new ExecuteDB();
				String mysql = "";
				int len = ByName.length();
				if (len > 0) {
						System.out.println("ByName:" + ByName);
						mysql = "select * from spare_parts where "+se+" like '%" + ByName + "%'order by 备件编码";
						System.out.println(mysql);

						ResultSet rs1 = null;
						rs1 = myExecuteDB.exeQuery(mysql);
						while (rs1.next()) {
							String name1 = rs1.getString("备件名称");
							String code1 = rs1.getString("备件编码");
							String unit1 = rs1.getString("备件单位");
							String place1 = rs1.getString("存放库区");
							String unitprice1 = rs1.getString("备件单价");
							String quntity1 = rs1.getString("备件质量");
							String stan1 = rs1.getString("备件规格");
							String safestoke1 = rs1.getString("安全库存");
							String pristoke1 = rs1.getString("原始库存");
							String pridemand1 = rs1.getString("初始年需求量");
							String ordercost1 = rs1.getString("订货成本");
							String unitpprice1 = rs1.getString("单位库存成本");
			%>
			<tr>
				<td><div align="center"><%=name1%></div></td>
				<td><div align="center"><%=code1%></div></td>
				<td><div align="center"><%=unit1%></div></td>
				<td><div align="center"><%=place1%></div></td>
				<td><div align="center"><%=unitprice1%></div></td>
				<td><div align="center"><%=quntity1%></div></td>
				<td><div align="center"><%=stan1%></div></td>
				<td><div align="center"><%=safestoke1%></div></td>
				<td><div align="center"><%=pristoke1%></div></td>
				<td><div align="center"><%=pridemand1%></div></td>
				<td><div align="center"><%=ordercost1%></div></td>
				<td><div align="center"><%=unitpprice1%></div></td>
			</tr>
			
			<%
				}
				}
			%>
		</tbody>
	</table>
	<div id="toolbar">
		<!-- #代表链接到当前页面 -->
		<a href="/courseDesignProject/basic_information/add.jsp"
			class="easyui-linkbutton" iconCls="icon-add" plain="true">添加物料信息</a>
		<!--  <a href="/courseDesignProject/basic_information/edit.jsp"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editUser()">修改删除信息</a> -->
		<label for="se">搜索范围：</label>
		<select id="se" class="easyui-combobox" name="dept" style="width:100px;">
    		<option value="备件名称">备件名称</option>
  		 	<option value="备件编码">备件编码</option>
   			<option value="存放库区">存放库区</option>
		</select>
		<input id="searchByName" class="easyui-textbox"
			style="line-height: 26px; border: 1px solid #ccc" value="<%=ByName%>"
			prompt="查询关键字"> <a href="#" class="easyui-linkbutton"
			plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
	</div>
	
	<script type="text/javascript">
	$('#se').combobox({  //为下拉框赋值
	    value:'<%=se%>'
	  });


	function doSearch() {
	    var $ByName = $('#searchByName');
	    var ByName = $ByName.val();
	    var $se = $('#se');
	    var se = $se.val();
	    //alert(start);
	   //alert("/courseDesignProject/searchservlt?ByName="+ByName);
	    window.location.href="/courseDesignProject/searchservlt?ByName="+ByName+"&se="+se;
	}


	
		function func1() {
			//document.getElementById("searchByName").focus();
			$('#searchByName').textbox('textbox').focus();   
			//光标定位到文本框searchByName
		}
		</script>
	
<script type="text/javascript" src="/courseDesignProject/js/search.js"></script>
<!-- 调用dosearch方法 -->	
</body>
</html>