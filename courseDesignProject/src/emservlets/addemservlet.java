package emservlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class addemservlet
 */
@WebServlet("/addemservlet")
public class addemservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addemservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	 
		String emnamestr = request.getParameter("emname").trim();
		String ennumstr = request.getParameter("ennum").trim();
		int ennumint = Integer.parseInt(ennumstr);
		String emsexstr = request.getParameter("emsex").trim();
		String embirthtdaystr = request.getParameter("embirthtday").trim();
		String emtelestr = request.getParameter("emtele").trim();
		int emteleint = Integer.parseInt(emtelestr);
		String ememailstr = request.getParameter("ememail").trim();
		String emaddressstr = request.getParameter("emaddress").trim();
		String remarksstr = request.getParameter("remarks").trim();
		
		System.out.println(emnamestr);
		
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="select count(*) as unumber from employee where 工号='" + ennumint + "'";
		System.out.println(strSql);
		int unumber=0;
		ResultSet rs = null;
		boolean isExist = false;
		boolean issuc =false;
		try {
			rs = myExecuteDB.exeQuery(strSql);
			while (rs.next()) {
				unumber = rs.getInt("unumber");
			}
			System.out.println("unumber:" + unumber);
			if (unumber > 0) {
				response.getWriter().write("已存在该员工工号!");
				isExist = true;
			} else {// 当数据表中没有相同的记录时，向数据库表添加新记录
				strSql="insert into employee (员工姓名,工号,性别,出生日期,电话,邮箱,住址,备注) values('"
				+ emnamestr + "','" + ennumint + "','" + emsexstr + "','"+embirthtdaystr+"','"+emteleint+"','"+ememailstr+"',"
						+ "'"+emaddressstr+"','"+remarksstr+"')";
				System.out.println(strSql);
				issuc=myExecuteDB.updateSql(strSql);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isExist) {
			//response.sendRedirect("/myfirst2017/sports/addsport.jsp");
		} else if(issuc) {
			response.sendRedirect("/courseDesignProject/employee/editem.jsp");
		}else{
			response.getWriter().append("Served at: ").append(request.getContextPath()).append("出现未知异常！");

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
