<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.imp {
	color: #F00;
}

.margin {
	margin-top: 50px;
	margin-left: 150px;
}
.ta{
	height:40px;
}
</style>
</head>
<body>
	<%
		String namestr = new String(request.getParameter("name").getBytes("ISO-8859-1"), "utf-8");
		String codestr = new String(request.getParameter("code").getBytes("ISO-8859-1"), "utf-8");
		String unitstr = new String(request.getParameter("unit").getBytes("ISO-8859-1"), "utf-8");
		//request.getParameter("sportName");解决中文乱码问题
		String placestr = new String(request.getParameter("place").getBytes("ISO-8859-1"), "utf-8");
		String unitpricestr = new String(request.getParameter("unitprice").getBytes("ISO-8859-1"), "utf-8");
		String quntitystr = new String(request.getParameter("quntity").getBytes("ISO-8859-1"), "utf-8");
		String stanstr = new String(request.getParameter("stan").getBytes("ISO-8859-1"), "utf-8");
		String safestokestr = new String(request.getParameter("safestoke").getBytes("ISO-8859-1"), "utf-8");
		String pristokestr = new String(request.getParameter("pristoke").getBytes("ISO-8859-1"), "utf-8");
		String pridemandstr = new String(request.getParameter("pridemand").getBytes("ISO-8859-1"), "utf-8");
		String ordercoststr = new String(request.getParameter("ordercost").getBytes("ISO-8859-1"), "utf-8");
		String unitppricestr = new String(request.getParameter("unitpprice").getBytes("ISO-8859-1"), "utf-8");
		System.out.println(namestr);
	%>

	<form action="/courseDesignProject/editservlt" method="post">
		<table width="60%" border="1" class="margin" cellspacing="0" style="font-size:18px;font-family:serif;">
			<tr style="background-color:#F8F9FF">
				<td colspan="2" class="ta">备件信息添加（<span class="imp">*为必填项</span>）
				</td>

			</tr>
			<tr>
				<td class="ta">备件名称<input id="name" name="name" type="text"
					value=<%=namestr%> /><span class="imp">*</span></td>
				<td class="ta">备件编码<%=codestr%><span class="imp">*</span></td>
			</tr>
			<tr>
				<td class="ta">备件单位<input name="unit" type="text" value=<%=unitstr%> /><span
					class="imp">*</span></td>
				<td class="ta">存放库区<select name="place" id="place">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
				</select></td>
			</tr>
			<tr>
				<td class="ta">备件单价<input name="unitprice" type="text"
					value=<%=unitpricestr%> /><span class="imp">*元</span></td>
				<td class="ta">备件质量<input name="quntity" type="text"
					value=<%=quntitystr%> /><span class="imp">*千克</span></td>
			</tr>
			<tr>
				<td class="ta">备件规格<input name="stan" type="text" value=<%=stanstr%> /><span
					class="imp">*</span></td>
				<td class="ta">安全库存<input name="safestoke" type="text"
					value=<%=safestokestr%> /><span class="imp">*</span></td>
			</tr>
			<tr>
				<td class="ta">原始库存<input name="pristoke" type="text"
					value=<%=pristokestr%> /><span class="imp">*</span></td>
				<td class="ta">初始年需求量<input name="pridemand" type="text"
					value=<%=pridemandstr%> /><span class="imp">*</span></td>
			</tr>
			<tr>
				<td class="ta">订货成本<input name="ordercost" type="text"
					value=<%=ordercoststr%> /><span class="imp">*元</span></td>
				<td class="ta">单位库存成本<input name="unitpprice" type="text"
					value=<%=unitppricestr%> /><span class="imp">*元</span></td>
			</tr>
			<tr>
				<td colspan="2" class="ta"><input type="submit" value="修改" /></td>

			</tr>
			<tr>
				<td colspan="2" class="ta"><span class="imp">备注：请输入有效数字!</span></td>

			</tr>
		</table>
		<input type="hidden" name="code" value="<%=codestr%>">
</form>
		 	<!--  <input type="hidden" value="<%=placestr%>" id="a" name="a"/> -->
	

 <script type="text/javascript">
 	var options =document.getElementById('place');
 	var place="<%=placestr%>";
 	//alert(place);
 	switch(place){
 	case "A":options[0].selected=true;break;
 	case "B":options[1].selected=true;break;
 	case "C":options[2].selected=true;break;
 	case "D":options[3].selected=true;break;
 	}
 	


	
	
	
	
	// var x = document.getElementById("a").value;
	 //alert(x);
	 //$("#place").val(x);
	 //$("#type").find("option[value=C]").attr("selected",true);
	 //var sel = document.getElementById("a");
     //var option = new Option(text, val);
     //sel.options.add(option);
	</script>
	<script type="text/javascript" src="/courseDesignProject/js/check.js">
    //使用外引入，加快的页面的加载速度，同时分离了不同类型的代码，查看的时候可以左右对照着看，查bug更方便
    </script>

</body>
</html>