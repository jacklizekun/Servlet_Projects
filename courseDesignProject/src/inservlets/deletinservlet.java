package inservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.inventory;
import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class deletinservlet
 */
@WebServlet("/deletinservlet")
public class deletinservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletinservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);	 
		String whnum = request.getParameter("whnum").trim();
		String codestr = request.getParameter("code").trim();
		String qunstr = request.getParameter("qun").trim();
		System.out.println(whnum);
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="delete from inpart where 入库单号='"+whnum+"'";
		System.out.println(strSql);
		try {
			myExecuteDB.updateSql(strSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		inventory myinventory=new inventory();
		
		int sin=myinventory.inven(codestr);//根据备件编码获取剩余库存
		System.out.println("sin="+sin);
		int qunint=Integer.parseInt(qunstr);
		sin=sin-qunint;//得到新的剩余库存
		System.out.println("sin2="+sin);
		
		boolean issuccess=false;
		issuccess=myinventory.inven(codestr,sin);//更新剩余库存
		if(issuccess==false){
			response.getWriter().append("Served at: ").append(request.getContextPath()).append("出现未知异常！");	
		}
		response.sendRedirect("/courseDesignProject/in/editin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
