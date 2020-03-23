package warehouseservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchwarehouseservlt
 */
@WebServlet("/searchwarehouseservlt")
public class searchwarehouseservlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchwarehouseservlt() {
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
		System.out.println(ByName);
		
		response.sendRedirect("/courseDesignProject/warehouse/searchwarehouse.jsp?ByName=" + java.net.URLEncoder.encode(ByName,"UTF-8")+"&se="+  java.net.URLEncoder.encode(se,"UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
