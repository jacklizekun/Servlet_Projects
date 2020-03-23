package inservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmgmt.ExecuteDB;

/**
 * Servlet implementation class editinservlet
 */
@WebServlet("/editinservlet")
public class editinservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editinservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);	 
		String whnum = request.getParameter("whnum").trim();
		String code = request.getParameter("scode").trim();
		String qun = request.getParameter("qun").trim();
		String buyprice = request.getParameter("buyprice").trim();
		String defaultloc =request.getParameter("defaultloc").trim();
		String supplier = request.getParameter("supplier").trim();
		String agent =request.getParameter("agent").trim();
		String remarks = request.getParameter("remarks").trim();
		
		System.out.println("whnum:"+whnum);

		ExecuteDB myExecuteDB=new ExecuteDB();
		String strSql="update inpart set 入库备件编码='"+ code +"',"
				+ "数量='"+qun+"',进货价='"+buyprice+"',默认库位='"+defaultloc+"',供货单位='"+supplier+"',"
				+ "经办人='"+agent+"',备注='"+remarks+"' where 入库单号='"+ whnum +"'";
		System.out.println(strSql);
		try {
			myExecuteDB.updateSql(strSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/courseDesignProject/in/editin.jsp");
		}
	}


