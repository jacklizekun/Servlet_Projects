package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class loginservlet
 */
@SuppressWarnings("unused")
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);
		String UserNamestr = request.getParameter("UserName").trim();
		String Passwdstr = request.getParameter("Passwd").trim();

		System.out.println(UserNamestr);
		System.out.println(Passwdstr);

		ExecuteDB myExecuteDB = new ExecuteDB();
		ResultSet rs = null;
		String strSql = "";
		
		

	

		//判断用户名是否存在，不清空用户名，重定向带参数跳转页面
		strSql = "select Uname from TUser where Uname='" + UserNamestr + "'";
		rs = myExecuteDB.exeQuery(strSql);
		String tmpUname="";
		try {
			while (rs.next()) {
				tmpUname=rs.getString("Uname");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(tmpUname.equals("")){
					
			System.out.println("wrong username!");
			String re="该用户名不存在!";
			System.out.println(re);
			response.sendRedirect("/courseDesignProject/login2.jsp?re="+ java.net.URLEncoder.encode(re,"UTF-8"));
		//	response.sendRedirect("/courseDesignProject/login.jsp");
			
			return;
		}
		
		//判断密码是否正确
		strSql = "select Upwd from TUser where Uname='" + UserNamestr + "'";
		rs = myExecuteDB.exeQuery(strSql);
		String tmpUpwd="";
		try {
			while (rs.next()) {
				tmpUpwd=rs.getString("Upwd");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!tmpUpwd.equals(Passwdstr)){
			//response.getWriter().write("wrong Upwd!");
			System.out.println("wrong Upwd!");
			String re="密码错误!";
			System.out.println(re);
			response.sendRedirect("/courseDesignProject/login2.jsp?re="+ java.net.URLEncoder.encode(re,"UTF-8"));
			return;
		}
	
		//判断用户名和密码在系统中存在的个数
		strSql = "select count(*) as unumber from TUser where Uname='" + UserNamestr + "' and Upwd='" + Passwdstr + "'";
		System.out.println(strSql);

		int unumber = 0;
		boolean loginstatus=false;
		try {
			rs = myExecuteDB.exeQuery(strSql);
			/*
			 while (rs.next()) {
				unumber++;
			}*/
		while (rs.next()) {
				unumber=rs.getInt("unumber");
			}
			System.out.println("unumber:"+unumber);
			if (unumber > 0) {
				if (unumber == 1) {
					//response.getWriter().write("login success!");
					loginstatus=true;
				}
				if (unumber > 1) {
					response.getWriter().write("too many user used same pwd!");
					return;
				}

			}else{
				response.getWriter().write("login failed!");
				String re="登陆失败!";
				System.out.println(re);
				response.sendRedirect("/courseDesignProject/login2.jsp?re="+ java.net.URLEncoder.encode(re,"UTF-8"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//session
		//在session中记录用户名和密码及登陆!是否成功的状态
		//在调用其他页面的时候在过滤器中验证

		if(loginstatus){
			HttpSession session = request.getSession();//没有Session就新建一个
			session.setAttribute("user",UserNamestr);//给session赋值，在session里登记
			response.sendRedirect("/courseDesignProject/index.jsp");
			return;
		}else{
			//response.sendRedirect("/courseDesignProject/login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
