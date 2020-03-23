package supplierservlets;

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
 * Servlet implementation class addsuppliersservlet
 */
@WebServlet("/addsuppliersservlet")
public class addsuppliersservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addsuppliersservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType( "text/html; charset=utf-8");	 
		String sunum = request.getParameter("sunum").trim();
		String suname = request.getParameter("suname").trim();
		String emnum = request.getParameter("emnum").trim();//负责人
		String sutele = request.getParameter("sutele").trim();
		String remarks = request.getParameter("remarks").trim();
		
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="select count(*) as unumber from supplier where 供应商编号='" + sunum + "'";
		
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
			response.getWriter().write("已存在该供应商编号!");
			}else{
				strSql="insert into supplier (供应商编号,供应商名称,供应商电话,负责人工号,供应商备注) values('"
						+ sunum + "','" + suname + "','" + sutele + "','"+emnum+"','"+remarks+"')";
				System.out.println(strSql);
				try {
					myExecuteDB.updateSql(strSql);
					response.sendRedirect("/courseDesignProject/supplier/editsupplier.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
			}
	}

}
