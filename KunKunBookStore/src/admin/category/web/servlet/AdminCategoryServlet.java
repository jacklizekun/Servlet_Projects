package admin.category.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import book.service.BookService;
import category.domain.Category;
import category.service.CategoryService;
import util.commons.CommonUtils;
import util.servlet.BaseServlet;

/**
 * 
 * @author 李泽坤
 *
 */
public class AdminCategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	private BookService bookService = new BookService(); 
	
	//查询所有分类
	public String findAll(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		req.setAttribute("parents", categoryService.findAll());
		return "f:/adminjsps/admin/category/list.jsp";
	}
	
	// 添加一级分类
	public String addParent(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		Category parent = CommonUtils.toBean(req.getParameterMap(), Category.class);
		parent.setCid(CommonUtils.uuid());//设置cid
		categoryService.add(parent);
		return findAll(req, resp);
	}
	
	public String addChild(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		Category child = CommonUtils.toBean(req.getParameterMap(), Category.class);
		child.setCid(CommonUtils.uuid());//设置cid
		String pid = req.getParameter("pid");
		Category parent = new Category();
		parent.setCid(pid);
		child.setParent(parent);
		categoryService.add(child);
		return findAll(req, resp);
	}
	

	public String addChildPre(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String pid = req.getParameter("pid");//当前点击的父分类id
		List<Category> parents = categoryService.findParents();
		req.setAttribute("pid", pid);
		req.setAttribute("parents", parents);	
		return "f:/adminjsps/admin/category/add2.jsp";
	}
	
	public String editParentPre(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
	
		String cid = req.getParameter("cid");
		Category parent = categoryService.load(cid);
		req.setAttribute("parent", parent);
		return "f:/adminjsps/admin/category/edit.jsp";
	}
	

	public String editParent(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		Category parent = CommonUtils.toBean(req.getParameterMap(), Category.class);
		categoryService.edit(parent);
		return findAll(req, resp);
	}
	

	public String editChildPre(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		String cid = req.getParameter("cid");
		Category child = categoryService.load(cid);
		req.setAttribute("child", child);
		req.setAttribute("parents", categoryService.findParents());
		
		return "f:/adminjsps/admin/category/edit2.jsp";
	}
	

	public String editChild(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		Category child = CommonUtils.toBean(req.getParameterMap(), Category.class);
		String pid = req.getParameter("pid");
		Category parent = new Category();
		parent.setCid(pid);
		child.setParent(parent);
		
		categoryService.edit(child);
		return findAll(req, resp);
	}
	
	public String deleteParent(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String cid = req.getParameter("cid");
		int cnt = categoryService.findChildrenCountByParent(cid);
		if(cnt > 0) {
			req.setAttribute("msg", "该分类下还有子分类，不能删除！");
			return "f:/adminjsps/msg.jsp";
		} else {
			categoryService.delete(cid);
			return findAll(req, resp);
		}
	}
	
	public String deleteChild(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		String cid = req.getParameter("cid");
		int cnt = bookService.findBookCountByCategory(cid);
		if(cnt > 0) {
			req.setAttribute("msg", "该分类下还存在图书，不能删除！");
			return "f:/adminjsps/msg.jsp";
		} else {
			categoryService.delete(cid);
			return findAll(req, resp);
		}
	}
}
