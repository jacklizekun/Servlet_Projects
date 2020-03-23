package inservlets;

import java.io.IOException;
import java.sql.ResultSet;

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
 * Servlet implementation class addinservlet
 */
@WebServlet("/addinservlet")
public class addinservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addinservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);	 
		String whnumstr = request.getParameter("whnum").trim();
		String codestr = request.getParameter("code").trim();
		String qunstr = request.getParameter("qun").trim();
		String buypricestr = request.getParameter("buyprice").trim();
		String defaultlocstr = request.getParameter("defaultloc").trim();
		String supplierstr = request.getParameter("supplier").trim();
		String agentstr = request.getParameter("agent").trim();
		String remarksstr = request.getParameter("remarks").trim();
		
		System.out.println(codestr);
		System.out.println(whnumstr);
		

		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time=df.format(new Date());
		System.out.println(time);// new Date()为获取当前系统时间
		
		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="select count(*) as unumber from inpart where 入库单号='" + whnumstr + "'";
		
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
				response.getWriter().write("已存在该入库单号!");
				isExist = true;
			} else {// 当数据表中没有相同的记录时，向数据库表添加新记录
				strSql="insert into inpart (入库单号,入库备件编码,数量,进货价,默认库位,供货单位,经办人,备注,入库时间) values('"
				+ whnumstr + "','" + codestr + "','" + qunstr + "','"+buypricestr+"','"+defaultlocstr+"','"+supplierstr+"','"+agentstr+"','"+remarksstr+"','"+time+"')";
				System.out.println(strSql);
				issuc=myExecuteDB.updateSql(strSql);
				boolean issuccess=false;
				
				
				inventory myinventory=new inventory();
				
				
				myinventory.isexist(codestr);//剩余库存表是否存在备件编码，如果没有就插入一个
				

				int sin=myinventory.inven(codestr);//根据备件编码获取剩余库存
				System.out.println("sin="+sin);
				int qunint=Integer.parseInt(qunstr);
				sin=sin+qunint;//得到新的剩余库存
				System.out.println("sin2="+sin);
				
				issuccess=false;
				issuccess=myinventory.inven(codestr,sin);//更新剩余库存
				if(issuccess==false){
					response.getWriter().append("Served at: ").append(request.getContextPath()).append("出现未知异常！");	
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isExist) {
			//response.sendRedirect("/myfirst2017/sports/addsport.jsp");
		} else if(issuc) {
			response.sendRedirect("/courseDesignProject/in/editin.jsp");
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
