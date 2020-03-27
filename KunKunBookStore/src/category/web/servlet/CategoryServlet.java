package category.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.domain.Category;
import category.service.CategoryService;
import util.servlet.BaseServlet;

/**
 * 分类模块WEB层
 * @author 李泽坤
 *
 */
public class CategoryServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private CategoryService categoryService = new CategoryService();	
	
	public String findAll(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/jsps/left.jsp";
	}
 }
