package admin.book.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import book.domain.Book;
import book.service.BookService;
import category.domain.Category;
import category.service.CategoryService;
import pager.PageBean;
import util.commons.CommonUtils;
import util.servlet.BaseServlet;


public class AdminBookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	private CategoryService  categoryService = new CategoryService();
	
	// 删除图书
	public String delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String bid = req.getParameter("bid");
		//删除图片
		Book book = bookService.load(bid);
		String savepath = this.getServletContext().getRealPath("/");//获取真实的路径
		new File(savepath, book.getImage_w()).delete();//删除文件
		new File(savepath, book.getImage_b()).delete();//删除文件
		
		bookService.delete(bid);//删除数据库的记录
		
		req.setAttribute("msg", "删除图书成功！");
		return "f:/adminjsps/msg.jsp";
	}
	
	// 修改图书
	public String edit(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		Map map = req.getParameterMap();
		Book book = CommonUtils.toBean(map, Book.class);
		Category category = CommonUtils.toBean(map, Category.class);
		book.setCategory(category);
		
		bookService.edit(book);
		req.setAttribute("msg", "修改图书成功！");
		return "f:/adminjsps/msg.jsp";
	}
	
	//加载图书
	public String load(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// 获取bid，得到Book对象，保存
		String bid = req.getParameter("bid");
		Book book = bookService.load(bid);
		req.setAttribute("book", book);
		// 获取所有一级分类，保存之
		req.setAttribute("parents", categoryService.findParents());
		//获取当前图书所属的一级分类下所有2级分类
		String pid = book.getCategory().getParent().getCid();
		req.setAttribute("children", categoryService.findChildren(pid));
		// 转发到desc.jsp显示
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	
	//添加图书
	public String addPre(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pid = req.getParameter("pid");
		List<Category> children = categoryService.findChildren(pid);
		String json = toJson(children);
		resp.getWriter().print(json);
		return null;
	}
	
	// {"cid":"fdsafdsa", "cname":"fdsafdas"}
	private String toJson(Category category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"cid\"").append(":").append("\"").append(category.getCid()).append("\"");
		sb.append(",");
		sb.append("\"cname\"").append(":").append("\"").append(category.getCname()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	// [{"cid":"fdsafdsa", "cname":"fdsafdas"}, {"cid":"fdsafdsa", "cname":"fdsafdas"}]
	private String toJson(List<Category> categoryList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < categoryList.size(); i++) {
			sb.append(toJson(categoryList.get(i)));
			if(i < categoryList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	// 显示所有分类
	public String findCategoryAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/adminjsps/admin/book/left.jsp";
	}
	
	//获取当前页码
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if(param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch(RuntimeException e) {}
		}
		return pc;
	}
	
	//截取url，页面中的分页导航中需要使用它做为超链接的目标！
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	//按分类查
	public String findByCategory(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		int pc = getPc(req);
		String url = getUrl(req);
		String cid = req.getParameter("cid");
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	//按作者查
	public String findByAuthor(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		String author = req.getParameter("author");
		PageBean<Book> pb = bookService.findByAuthor(author, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	// 按出版社查询
	public String findByPress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		String press = req.getParameter("press");
		PageBean<Book> pb = bookService.findByPress(press, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	//按图名查
	public String findByBname(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		String bname = req.getParameter("bname");
		PageBean<Book> pb = bookService.findByBname(bname, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	// 多条件组合查询
	public String findByCombination(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		Book criteria = CommonUtils.toBean(req.getParameterMap(), Book.class);
		PageBean<Book> pb = bookService.findByCombination(criteria, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
}
