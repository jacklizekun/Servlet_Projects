package servlets;

import dbmgmt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.google.gson.Gson;

//import bean.PeBean;

/**
 * Servlet implementation class searchservlt
 */
@WebServlet("/searchservlt")
public class searchservlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public searchservlt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);
		String ByName =new String(request.getParameter("ByName").trim().getBytes("iso-8859-1"),"utf-8");
		String se =new String(request.getParameter("se").trim().getBytes("iso-8859-1"),"utf-8");
		System.out.println(ByName);
		//System.out.println("/courseDesignProject/basic_information/search.jsp?ByName="+ByName);
		//response.sendRedirect("/courseDesignProject/basic_information/search.jsp?ByName="+ByName);
		response.sendRedirect("/courseDesignProject/basic_information/search.jsp?ByName=" + java.net.URLEncoder.encode(ByName,"UTF-8")+"&se="+  java.net.URLEncoder.encode(se,"UTF-8"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
