package admin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.domain.Order;
import order.service.OrderService;
import pager.PageBean;
import user.domain.User;
import util.servlet.BaseServlet;


public class AdminOrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();
	
	// 获取当前页码
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
	
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	// 查看所有订单
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		PageBean<Order> pb = orderService.findAll(pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	//按状态查询
	public String findByStatus(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		int status = Integer.parseInt(req.getParameter("status"));
		PageBean<Order> pb = orderService.findByStatus(status, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	// 查看订单详细信息
	public String load(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String oid = req.getParameter("oid");
		Order order = orderService.load(oid);
		req.setAttribute("order", order);
		String btn = req.getParameter("btn");//btn说明了用户点击哪个超链接来访问本方法的
		req.setAttribute("btn", btn);
		return "/adminjsps/admin/order/desc.jsp";
	}
	
	//取消订单
	public String cancel(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String oid = req.getParameter("oid");
		//校验订单状态
		int status = orderService.findStatus(oid);
		if(status != 1) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能取消！");
			return "f:/adminjsps/msg.jsp";
		}
		orderService.updateStatus(oid, 5);//设置状态为取消！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "您的订单已取消，您不后悔吗！");
		return "f:/adminjsps/msg.jsp";		
	}
	
	// 发货功能
	public String deliver(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String oid = req.getParameter("oid");

		int status = orderService.findStatus(oid);
		if(status != 2) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能发货！");
			return "f:/adminjsps/msg.jsp";
		}
		orderService.updateStatus(oid, 3);//设置状态为取消！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "您的订单已发货，请查看物流，马上确认吧！");
		return "f:/adminjsps/msg.jsp";		
	}
}
