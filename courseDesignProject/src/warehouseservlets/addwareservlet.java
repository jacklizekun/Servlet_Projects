package warehouseservlets;

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
 * Servlet implementation class addwareservlet
 */
@WebServlet("/addwareservlet")
public class addwareservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addwareservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType( "text/html; charset=utf-8");	 
		String wanum = request.getParameter("wanum").trim();
		String agent = request.getParameter("agent").trim();
		String watele = request.getParameter("watele").trim();
		String remarks = request.getParameter("remarks").trim();
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="select count(*) as unumber from warehouse where 仓库编号='" + wanum + "'";
		
		System.out.println("agent"+agent);
		int unumber=0;
		ResultSet rs = null;
		rs = myExecuteDB.exeQuery(strSql);
		try {
			while (rs.next()) {
				unumber = rs.getInt("unumber");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("unumber:" + unumber);
		if (unumber > 0) {
			response.getWriter().write("已存在该仓库编号!");
			}else{
				strSql="insert into warehouse (仓库编号,仓库负责人工号,仓库电话,仓库备注) values('"
						+ wanum + "','" + agent + "','" + watele + "','"+remarks+"')";
				System.out.println(strSql);
				try {
					myExecuteDB.updateSql(strSql);
					response.sendRedirect("/courseDesignProject/warehouse/editwarehouse.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
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
