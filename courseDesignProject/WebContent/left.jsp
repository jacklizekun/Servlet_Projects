<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

.container {
	width: 960px;
	background: #FFF;
	margin: 0 auto; /* 侧边的自动值与宽度结合使用，可以将布局居中对齐 */
	overflow: hidden; /* 此声明可使 .container 了解其内部浮动列的结束位置以及包含列的位置 */
}

.sidebar1 {
	float: left;
	width: 180px;
	background:;
	padding-bottom: 10px;
}

ul.nav {
	list-style: none; /* 这将删除列表标记 */
	border-top: 1px solid #666; /* 这将为链接创建上边框 – 使用下边框将所有其它项放置在 LI 中 */
	margin-bottom: 15px; /* 这将在下面内容的导航之间创建间距 */
}

ul.nav li {
	border-bottom: 1px solid #666; /* 这将创建按钮间隔 */
}

ul.nav a, ul.nav a:visited { /* 对这些选择器进行分组可确保链接即使在访问之后也能保持其按钮外观 */
	padding: 5px 5px 5px 15px;
	display: block; /* 这将为链接赋予块属性，使其填满包含它的整个 LI。这样，整个区域都可以响应鼠标单击操作。 */
	text-decoration: none;
	background:;
	

}
</style>
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
<body bgcolor="#F8F9FF">
	<div>
		<div class="sidebar1">
		<div id="p" class="easyui-panel" title="" style="width:100%;height:90%;padding:10px;">
			<ul class="nav">
			
			<li><span>货物信息维护</span></li>	
				<li><a href="/courseDesignProject/in/addin.jsp" target="mainFrame">入库信息登记</a></li>
				<li><a href="/courseDesignProject/in/editin.jsp" target="mainFrame">入库管理</a></li>
				<li><span>&nbsp;</span></li>	
				<li><a href="/courseDesignProject/ex/addex.jsp" target="mainFrame">出库信息登记</a></li>
				<li><a href="/courseDesignProject/ex/editex.jsp" target="mainFrame">出库管理</a></li>
				<li><span>&nbsp;</span></li>	
				<li><a href="/courseDesignProject/inventory/inventory_show.jsp" target="mainFrame">库存信息显示与查询</a></li>
				<li><span>&nbsp;</span></li>	
				<li><a href="/courseDesignProject/tracking/tracking.jsp" target="mainFrame">跟踪查询</a></li>
				<li><span>&nbsp;</span></li>	
			<li><span>员工信息维护 </span></li>
				<li><a href="/courseDesignProject/employee/addem.jsp" target="mainFrame">添加员工信息</a></li>
				<li><a href="/courseDesignProject/employee/editem.jsp" target="mainFrame">员工信息管理</a></li>
			<li><span>&nbsp;</span></li>	
			<li><span>基础信息维护 </span></li>
				<!--<li><a href="/courseDesignProject/basic_information/show_all.jsp" target="mainFrame">显示物料信息 </a></li>-->
				<li><a href="/courseDesignProject/basic_information/add.jsp" target="mainFrame">添加物料信息</a></li>
				<li><a href="/courseDesignProject/basic_information/edit.jsp" target="mainFrame">物料信息管理</a></li>
				<li><span>&nbsp;</span></li>
			<li><span>仓库信息维护 </span></li>
				<!--<li><a href="/courseDesignProject/basic_information/show_all.jsp" target="mainFrame">显示物料信息 </a></li>-->
				<li><a href="/courseDesignProject/warehouse/addwarehouse.jsp" target="mainFrame">添加仓库信息</a></li>
				<li><a href="/courseDesignProject/warehouse/editwarehouse.jsp" target="mainFrame">仓库信息管理</a></li>
			<li><span>&nbsp;</span></li>
			<li><span>供应商信息维护 </span></li>
				<!--<li><a href="/courseDesignProject/basic_information/show_all.jsp" target="mainFrame">显示物料信息 </a></li>-->
				<li><a href="/courseDesignProject/supplier/addsupplier.jsp" target="mainFrame">添加供应商信息</a></li>
				<li><a href="/courseDesignProject/supplier/editsupplier.jsp" target="mainFrame">仓库供应商管理</a></li>
				<li><span>&nbsp;</span></li>		
				<li><span>&nbsp;</span></li>		
				<li><span>&nbsp;</span></li>
				<li><span>&nbsp;</span></li>
				<li><span>&nbsp;</span></li>
			</ul>
			</div>
		</div>
	</div>
</body>
</html>