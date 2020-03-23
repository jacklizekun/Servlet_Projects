package exservlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class addexservlet
 */
@WebServlet("/addexservlet")
public class addexservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addexservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	
		String code =request.getParameter("code");
		String strSql="select 备件名称 from spare_parts where 备件编码='"+code+"'";
		ExecuteDB myExecuteDB=new ExecuteDB();
		ResultSet rs=myExecuteDB.exeQuery(strSql);
		String name="";
		try {
			while (rs.next()) {
				name = rs.getString("备件名称");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		response.sendRedirect("/courseDesignProject/ex/addex2.jsp?name="+java.net.URLEncoder.encode(name,"UTF-8")+"&code="+java.net.URLEncoder.encode(code,"UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
