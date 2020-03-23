package servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginfiler implements Filter {
//底层拦截 继承Filter 

	public loginfiler() {
	}
	
	@Override
	public void destroy() {
		
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("start filter");
		HttpServletRequest myrequest = (HttpServletRequest)request;
		//System.out.println(myrequest.getContextPath());
		System.out.println(myrequest.getRequestURI());  //　getRequestURL方法返回客户端发出请求时的完整URL
		String creqfullurl=myrequest.getRequestURI();
		
	

		if(creqfullurl.contains("jquery-easyui-1.8.1")||creqfullurl.contains("login.jsp")||creqfullurl.contains("loginservlet")||creqfullurl.contains("login2.jsp")){
			//判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
			//登陆界面需要用到的文件不做判断
			chain.doFilter(request, response);
			return;
		}
		// 如果session能够取到，说明用户已经登录
		// 此处不新建session，只是去取已经创建的session
		HttpSession session = ((HttpServletRequest) request).getSession();
		String user = "";
		if (session != null) {
			System.out.println("1");
			user = (String) session.getAttribute("user");//从session中取出user并赋值
			System.out.println("user:"+user);
			if (user == null || user.equals("")) {
				((HttpServletResponse) response).sendRedirect("/courseDesignProject/login.jsp");
			} else {
				chain.doFilter(request, response);
				return;
			}
		}
		// 否则，说明用户没有登录，跳转到登录页面让用户登录
		else {
			((HttpServletResponse) response).sendRedirect("/courseDesignProject/login.jsp");
			return;
		}
		// pass the request along the filter chain
	}


	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
