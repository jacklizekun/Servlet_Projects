package web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PageModel;
import domain.Product;
import service.ProductService;
import service.serviceImp.ProductServiceImp;
import web.base.BaseServlet;

public class ProductServlet extends BaseServlet {
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		ProductService ProductService=new ProductServiceImp();
		Product product=ProductService.findProductByPid(pid);
		request.setAttribute("product", product);
		return "/jsp/product_info.jsp";
	}
	
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid=request.getParameter("cid");
		int curNum=Integer.parseInt(request.getParameter("num"));
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findProductsByCidWithPage(cid,curNum);
		request.setAttribute("page", pm);
		return  "/jsp/product_list.jsp";
	}
	
}
