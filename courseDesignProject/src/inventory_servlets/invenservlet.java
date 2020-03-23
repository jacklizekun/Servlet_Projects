package inventory_servlets;

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
 * Servlet implementation class invenservlet
 */
@WebServlet("/invenservlet")
public class invenservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public invenservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String ByName =new String(request.getParameter("ByName").trim().getBytes("iso-8859-1"),"utf-8");
		String se =new String(request.getParameter("se").trim().getBytes("iso-8859-1"),"utf-8");
		System.out.println("se="+se);
		System.out.println("ByName="+ByName);
		
		//String strSql="select 剩余库存 from inventory left join spare_parts on inventory.库存备件编码=spare_parts.备件编码 where "+se+"='"+ByName+"'";
		
		response.sendRedirect("/courseDesignProject/inventory/invensearchshow.jsp?ByName=" +java.net.URLEncoder.encode(ByName,"UTF-8")+"&se=" + java.net.URLEncoder.encode(se,"UTF-8"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
