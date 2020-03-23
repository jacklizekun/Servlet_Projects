package servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbmgmt.*;
/**
 * Servlet implementation class addservlet
 */
@WebServlet("/addservlet")
public class addservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	 
		String namestr = request.getParameter("name").trim();
		String codestr = request.getParameter("code").trim();
		String unitstr = request.getParameter("unit").trim();
		String placestr = request.getParameter("place").trim();
		String unitpricestr = request.getParameter("unitprice").trim();
		String quntity = request.getParameter("quntity").trim();
		String stanstr = request.getParameter("stan").trim();
		String safestokestr = request.getParameter("safestoke").trim();
		String pristokestr = request.getParameter("pristoke").trim();
		
		String pridemandstr = request.getParameter("pridemand").trim();
		
		String ordercoststr = request.getParameter("ordercost").trim();
		String unitppricestr = request.getParameter("unitpprice").trim();
		
		System.out.println(codestr);
		System.out.println(unitstr);
		
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="select count(*) as unumber from spare_parts where 备件编码='" + codestr + "'";
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
				response.getWriter().write("已存在该备件编码!");
				isExist = true;
			} else {// 当数据表中没有相同的记录时，向数据库表添加新记录
				strSql="insert into spare_parts (备件名称,备件编码,备件单位,存放库区,备件单价,备件质量,备件规格,安全库存,原始库存,初始年需求量,订货成本,单位库存成本) values('"
				+ namestr + "','" + codestr + "','" + unitstr + "','"+placestr+"','"+unitpricestr+"','"+quntity+"','"+stanstr+"','"+safestokestr+"','"+pristokestr+"','"+pridemandstr+"','"+ordercoststr+"','"+unitppricestr+"')";
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
			response.sendRedirect("/courseDesignProject/basic_information/edit.jsp");
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
