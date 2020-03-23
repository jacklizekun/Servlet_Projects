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
import bean.sinventory;
/**
 * Servlet implementation class addexservlet2
 */
@WebServlet("/addexservlet2")
public class addexservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addexservlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	
		String namestr =request.getParameter("name");
		String codestr =request.getParameter("code");
		String numstr=request.getParameter("num");	
		
		int sinventory=0;//剩余库存
		String placestr="";
		
		ExecuteDB myExecuteDB = new ExecuteDB();
		String strSql = "select DATE_FORMAT(入库时间,'%Y-%m-%d %H:%i:%S')as timein,备件编码,入库单号,备件名称,数量,进货价,默认库位,供货单位,经办人,备注 from inpart left join spare_parts on inpart.入库备件编码=spare_parts.备件编码 where inpart.入库单号="
				+ numstr;
		System.out.println(strSql);
		ResultSet rs = myExecuteDB.exeQuery(strSql);
		try {
			while (rs.next()) {
		
				placestr = rs.getString("默认库位");
}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sinventory mysinventory=new sinventory();
		sinventory=mysinventory.sin(numstr);
		System.out.println("sinventory:"+sinventory);
		
		response.sendRedirect("/courseDesignProject/ex/addex3.jsp?name="
		+java.net.URLEncoder.encode(namestr,"UTF-8")+"&code="+java.net.URLEncoder.encode(codestr,"UTF-8")
		+"&num="+java.net.URLEncoder.encode(numstr,"UTF-8")+"&sinventory="+sinventory+"&place="
		+java.net.URLEncoder.encode(placestr,"UTF-8"));
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
