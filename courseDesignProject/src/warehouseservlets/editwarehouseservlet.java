package warehouseservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class editwarehouseservlet
 */
@WebServlet("/editwarehouseservlet")
public class editwarehouseservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editwarehouseservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType( "text/html; charset=utf-8");	 
		String warnum = request.getParameter("warnum").trim();
		String agent = request.getParameter("agent").trim();
		String watele = request.getParameter("watele").trim();
		String remarks = request.getParameter("remarks").trim();
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="update warehouse set 仓库负责人工号='"+ agent +"',"
				+ "仓库电话='"+watele+"',仓库备注='"+remarks+"' where 仓库编号='"+ warnum +"'";
		System.out.println(strSql);
		try {
			myExecuteDB.updateSql(strSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/courseDesignProject/warehouse/editwarehouse.jsp");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
