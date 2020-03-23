package emservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class editemservlet
 */
@WebServlet("/editemservlet")
public class editemservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editemservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	
		String emnamestr =request.getParameter("emname");
		String ennumstr =request.getParameter("ennum");
		int ennumint=Integer.parseInt(ennumstr);
		String emsexstr =request.getParameter("emsex");
		String embirthtdaystr =request.getParameter("embirthtday");
		String emtelestr = request.getParameter("emtele");
		int emteleint = Integer.parseInt(emtelestr);
		String ememailstr =request.getParameter("ememail");
		String emaddressstr = request.getParameter("emaddress");
		String remarksstr = request.getParameter("remarks");
		
		System.out.println("emnamestr:"+emnamestr);
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="update employee set 员工姓名='"+ emnamestr + "',性别='"+ emsexstr +"',"
				+ "出生日期='"+embirthtdaystr+"',电话='"+emteleint+"',邮箱='"+ememailstr+"',住址='"+emaddressstr+"',"
				+ "备注='"+remarksstr+"' where 工号='"+ ennumint +"'";
		System.out.println(strSql);
		try {
			myExecuteDB.updateSql(strSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/courseDesignProject/employee/editem.jsp");
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
