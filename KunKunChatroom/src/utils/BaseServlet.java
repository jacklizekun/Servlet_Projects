package utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	//传入请求
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
	//获取方法参数
		String methodName = req.getParameter("method");
	//默认执行execute
		if(methodName == null || methodName.isEmpty()) {
			methodName = "execute";
		}
	//获取当前类对象
		Class c = this.getClass();
		try {
			// 通过方法名称获取方法的反射对象
			Method m = c.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			// 反射方法目标方法
			String result = (String) m.invoke(this, req, res);
			// 通过返回值完成请求转发
			if(result != null && !result.isEmpty()) {
				req.getRequestDispatcher(result).forward(req, res);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}