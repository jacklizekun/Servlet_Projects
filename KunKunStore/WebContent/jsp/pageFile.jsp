<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%--分页显示的开始 --%>
    	<div style="text-align:center">
    		共${page.totalPageNum}页/第${page.currentPageNum}页
    		
    		<a href="${pageContext.request.contextPath}/${page.url}&num=1">首页</a>
    		<a href="${pageContext.request.contextPath}/${page.url}&num=${page.prePageNum}">上一页</a>
    		<c:forEach begin="${page.startPage}" end="${page.endPage}" var="pagenum">
    			<a href="${pageContext.request.contextPath}/${page.url}&num=${pagenum}">${pagenum}</a>
    		</c:forEach>
    		<a href="${pageContext.request.contextPath}/${page.url}&num=${page.nextPageNum}">下一页</a>
    		<a href="${pageContext.request.contextPath}/${page.url}&num=${page.totalPageNum}">末页</a>
    		<input type="text" id="pagenum" name="pagenum" size="1"/><input type="button" value="前往" onclick="jump()" />
    		<script type="text/javascript">
    			function jump(){
    				var totalpage = ${page.totalPageNum};
    				var pagenum = document.getElementById("pagenum").value;
    				var reg =/^[1-9][0-9]{0,1}$/;
    				if(!reg.test(pagenum)){
    					alert("请输入符合规定的数字");
    					return ;
    				}
    				if(parseInt(pagenum)>parseInt(totalpage)){

    					alert("不能大于总页数");
    					return;
    				}
    				window.location.href="${pageContext.request.contextPath}/${page.url}&num="+pagenum;
    			}
    		</script>
    	</div>
    	<%--分页显示的结束--%>
