package supplierservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class editsupplierservlet
 */
@WebServlet("/editsupplierservlet")
public class editsupplierservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editsupplierservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType( "text/html; charset=utf-8");	 
		String sunum = request.getParameter("sunum").trim();
		String suname = request.getParameter("suname").trim();
		String emnum = request.getParameter("emnum").trim();
		String sutele = request.getParameter("sutele").trim();
		String remarks = request.getParameter("remarks").trim();
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="update supplier set 供应商名称='"+ suname +"',"
				+ "供应商电话='"+sutele+"',负责人工号='"+emnum+"',供应商备注='"+remarks+"' where 供应商编号='"+ sunum +"'";
		
		System.out.println(strSql);
		try {
			myExecuteDB.updateSql(strSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/courseDesignProject/supplier/editsupplier.jsp");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
