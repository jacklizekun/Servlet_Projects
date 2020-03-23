package exservlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.inventory;
import dbmgmt.ExecuteDB;

import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * Servlet implementation class addexservlet3
 */
@WebServlet("/addexservlet3")
public class addexservlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addexservlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	
		String name =request.getParameter("name");
		String code =request.getParameter("code");//编号
		String num =request.getParameter("num");//入库单号
		String exnum =request.getParameter("exnum");//出库单号
		String place =request.getParameter("place");
		String exqun =request.getParameter("exqun");//出库数量
		String exprice =request.getParameter("exprice");//出库单价
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String extime=df.format(new Date());
		System.out.println(extime);// 出库时间
		
		String agent =request.getParameter("agent");//出库经办人
		String remarks =request.getParameter("remarks");
		
		ExecuteDB myExecuteDB=new ExecuteDB();
		
		String strSql="select count(*) as unumber from expart where 出库单号='" + exnum + "'";
		ResultSet rs = null;
		int unumber=0;
		boolean issuc =false;
		rs = myExecuteDB.exeQuery(strSql);
		try {
			while (rs.next()) {
				unumber = rs.getInt("unumber");
				System.out.println("unumber:" + unumber);
				if (unumber > 0) {
					response.getWriter().write("已存在该出库单号!");}
				else{
					strSql="insert into expart (出库备件编码,入库单号,库位,出库数量,出库单价,出库时间,经办人,备注,出库单号) values('"
						+ code + "','" + num + "','" + place + "','"+exqun+"','"+exprice+"','"+extime+"','"+agent+"','"+remarks+"','"+exnum+"')";
						System.out.println(strSql);
						
						try {
							myExecuteDB.updateSql(strSql);
							issuc=true;
							
							
							inventory myinventory=new inventory();
							int sin=myinventory.inven(code);//根据备件编码获取剩余库存
							System.out.println("sin="+sin);
							int qunint=Integer.parseInt(exqun);//出库数量
							sin=sin-qunint;//得到新的剩余库存
							System.out.println("sin2="+sin);
							boolean issuccess=false;
							issuccess=myinventory.inven(code,sin);//更新剩余库存
							if(issuccess==false){
								response.getWriter().append("Served at: ").append(request.getContextPath()).append("出现未知异常！");	
							}
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(issuc) {
			response.sendRedirect("/courseDesignProject/ex/editex.jsp");
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
