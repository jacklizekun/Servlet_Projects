package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class editservlt
 */
@WebServlet("/editservlt")
public class editservlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editservlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);	 
		String name = request.getParameter("name").trim();
		String code = request.getParameter("code").trim();
		String unit = request.getParameter("unit").trim();
		String place = request.getParameter("place").trim();
		String unitprice =request.getParameter("unitprice").trim();
		String quntity = request.getParameter("quntity").trim();
		String stan =request.getParameter("stan").trim();
		String safestoke = request.getParameter("safestoke").trim();
		String pristoke = request.getParameter("pristoke").trim();
		String pridemand = request.getParameter("pridemand").trim();
		String ordercost = request.getParameter("ordercost").trim();
		String unitpprice =request.getParameter("unitpprice").trim();
		System.out.println(ordercost);
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="update spare_parts set 备件名称='"+ name + "',备件单位='"+ unit +"',"
				+ "存放库区='"+place+"',备件单价='"+unitprice+"',备件质量='"+quntity+"',备件规格='"+stan+"',"
				+ "安全库存='"+safestoke+"',原始库存='"+pristoke+"',初始年需求量='"+pridemand+"',"
				+ "订货成本='"+ordercost+"',单位库存成本='"+unitpprice+"' where 备件编码='"+ code +"'";
		System.out.println(strSql);
		try {
			myExecuteDB.updateSql(strSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/courseDesignProject/basic_information/edit.jsp");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
