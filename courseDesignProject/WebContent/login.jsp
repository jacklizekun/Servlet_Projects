<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>坤坤企业维修物料仓储管理系系统登录</title>

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
body {
	margin: 0;
	padding: 0;
	background-color: rgb(248, 248, 255);
}

body, html {
	height: 100%;
}

.login {
	padding: 20px;
}

.login h2 {
	font-family: "微软雅黑";
	font-size: 20px;
	text-align: center;
}

.main {
	height: 100%;
	width:;
	display: flex;
	justify-content: center; /*flex布局 主轴方向居中*/
	align-items: center; /*侧轴居中*/
}

.hew {
	margin: 10px;
}

h1 {
	font-family: "微软雅黑";
	font-size: 20px;
	text-align: right;
	color:rgb(70,130,180);
	letter-spacing:3px;
}
</style>
</head>
<body>

	<div class="main">
			<div class="hew">
			<h1>企业维修物料仓储管理系统欢迎您！</h1>
		</div>
		<div class="login">
			<h2>用户系统登录</h2>
			<form name="loginform" id="lf" method="post"
				action="/courseDesignProject/loginservlet">
				<div style="margin: 20px 0;"></div>
				<div class="easyui-panel" style="width: 400px; padding: 50px 60px">
					<div style="margin-bottom: 20px">
						<input name="UserName" class="easyui-textbox" label="用户名:"
							prompt="username" iconWidth="28"
							style="width: 100%; height: 34px; padding: 10px">
					</div>
					<div style="margin-bottom: 20px">
						<input name="Passwd" class="easyui-passwordbox" label="密码:"
							prompt="password" iconWidth="28"
							style="width: 100%; height: 34px; padding: 10px">
					</div>
					<div id="dlg-buttons">
						<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
							style="width: 30%; height: 32px;" onclick="login()">登录</a>
					</div>
				</div>
			</form>
		</div>
	
	</div>
	
	<script type="text/javascript">
		function login() {
			$('#lf').submit();
		}
	</script>
</body>
</html>