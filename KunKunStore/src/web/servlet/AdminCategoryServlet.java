package web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import service.CategoryService;
import service.serviceImp.CategoryServiceImp;
import utils.UUIDUtils;
import web.base.BaseServlet;
/**
 * 
 * @author 李泽坤
 *
 */
public class AdminCategoryServlet extends BaseServlet {
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list=CategoryService.getAllCats();
		req.setAttribute("allCats", list);
		return "/admin/category/list.jsp";
	}
	
	public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/admin/category/add.jsp";
	}

	public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cname=req.getParameter("cname");
		String id=UUIDUtils.getId();
		Category c=new Category();
		c.setCid(id);
		c.setCname(cname);
		CategoryService CategoryService=new CategoryServiceImp();
		CategoryService.addCategory(c);
		resp.sendRedirect("${pageContext.request.contextPath}/AdminCategoryServlet?method=findAllCats");
		return null;
	}
}
