package book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.commons.CommonUtils;
import book.domain.Book;
import book.service.BookService;
import pager.PageBean;
import util.servlet.BaseServlet;
/**
 * 图书模块的WEB层
 * @author 李泽坤
 *
 */
public class BookServlet extends BaseServlet {
	//创建业务层
	private BookService bookService = new BookService();
	
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
	
	//截取url，页面中的分页导航中需要使用它做为超链接的目标
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	//按bid查询
	public String load(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String bid = req.getParameter("bid");//获取链接的参数bid
		Book book = bookService.load(bid);//通过bid得到book对象
		req.setAttribute("book", book);//保存到req中
		return "f:/jsps/book/desc.jsp";//转发到desc.jsp
	}
	
	//按分类查
	public String findByCategory(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//得到pc：如果页面传递，使用页面的，如果没传，pc=1
		int pc = getPc(req);
		//得到url：...
		String url = getUrl(req);
		//获取查询条件，本方法就是cid，即分类的id
		String cid = req.getParameter("cid");
		//使用pc和cid调用service#findByCategory得到PageBean
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		// 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	
	//按作者查
	public String findByAuthor(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//得到pc：如果页面传递，使用页面的，如果没传，pc=1
		int pc = getPc(req);
		//得到url：...
		String url = getUrl(req);
		//获取查询条件，本方法就是cid，即分类的id
		String author = req.getParameter("author");
		//使用pc和cid调用service#findByCategory得到PageBean
		PageBean<Book> pb = bookService.findByAuthor(author, pc);
		// 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	
	//按出版社查询
	public String findByPress(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//得到pc：如果页面传递，使用页面的，如果没传，pc=1
		int pc = getPc(req);
		//得到url：...
		String url = getUrl(req);
		//获取查询条件，本方法就是cid，即分类的id
		String press = req.getParameter("press");
		//使用pc和cid调用service#findByCategory得到PageBean
		PageBean<Book> pb = bookService.findByPress(press, pc);
		//给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	

	//按图名查
	public String findByBname(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		String bname = req.getParameter("bname");
		PageBean<Book> pb = bookService.findByBname(bname, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	
	//多条件组合查询
	public String findByCombination(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		Book criteria = CommonUtils.toBean(req.getParameterMap(), Book.class);
		PageBean<Book> pb = bookService.findByCombination(criteria, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
}
