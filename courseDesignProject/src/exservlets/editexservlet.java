package exservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class editexservlet
 */
@WebServlet("/editexservlet")
public class editexservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editexservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	 
		String exnum = request.getParameter("exnum").trim();
		String exqun = request.getParameter("exqun").trim();
		String exprice = request.getParameter("exprice").trim();
		String agent = request.getParameter("agent").trim();
		String remarks =request.getParameter("remarks").trim();
		
		System.out.println("exnum:"+exnum);
		
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="update expart set 出库数量='"+ exqun +"',"
				+ "出库单价='"+exprice+"',经办人='"+agent+"',备注='"+remarks+"' where 出库单号='"+ exnum +"'";
		System.out.println(strSql);
		try {
			myExecuteDB.updateSql(strSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/courseDesignProject/ex/editex.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
