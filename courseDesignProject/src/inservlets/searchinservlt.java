package inservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchinservlt
 */
@WebServlet("/searchinservlt")
public class searchinservlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchinservlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String starttimestr =new String(request.getParameter("starttime").trim().getBytes("iso-8859-1"),"utf-8");
		String endtimestr =new String(request.getParameter("endtime").trim().getBytes("iso-8859-1"),"utf-8");
		System.out.println(starttimestr);
		System.out.println(endtimestr);
		//System.out.println("/courseDesignProject/basic_information/search.jsp?ByName="+ByName);
		//response.sendRedirect("/courseDesignProject/basic_information/search.jsp?ByName="+ByName);
		response.sendRedirect("/courseDesignProject/in/searchin.jsp?starttime=" + java.net.URLEncoder.encode(starttimestr,"UTF-8")+"&endtime="+ java.net.URLEncoder.encode(endtimestr,"UTF-8"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
