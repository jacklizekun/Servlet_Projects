<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>坤坤企业维修物料仓储管理系统</title>
</head>

<frameset rows="80,*" cols="*" frameborder="yes" border="5"
	framespacing="0">
	<!-- rows高度 cols列宽 加上，*。它告诉浏览器要在将相邻的框架放入框架集之后，给剩下的空间分配各自的行或列。 -->
	<frame src="top.jsp" name="topFrame" scrolling="NO" noresize>
	<frameset cols="200,*" frameborder="yes" border="5" framespacing="0">
		<frame src="left.jsp" name="leftFrame" scrolling="NO" noresize>
		<frame src="main.jsp" name="mainFrame">
	</frameset>
</frameset>
<noframes>
	<body>您的浏览器无法处理框架！
	</body>
</noframes>
</html>