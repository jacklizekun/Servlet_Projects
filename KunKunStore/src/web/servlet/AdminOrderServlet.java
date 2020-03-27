package web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Order;
import service.OrderService;
import service.serviceImp.OrderServiceImp;
import web.base.BaseServlet;
import net.sf.json.JSONArray;
/**
 * 
 * @author 李泽坤
 *
 */
public class AdminOrderServlet extends BaseServlet {

	public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		OrderService OrderService=new OrderServiceImp();
		String st=req.getParameter("state");
		List<Order> list=null;
		if(null==st||"".equals(st)){
			list=OrderService.findAllOrders();			
		}else{
			list=OrderService.findAllOrders(st);
		}
		req.setAttribute("allOrders", list);
		return "/admin/order/list.jsp";
	}

	public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String oid=req.getParameter("id");
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
		String jsonStr=JSONArray.fromObject(order.getList()).toString();
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println(jsonStr);
		return null;
	}
	
	public String updateOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String oid=req.getParameter("oid");
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
		order.setState(3);
		OrderService.updateOrder(order);
		resp.sendRedirect("${pageContext.request.contextPath}/AdminOrderServlet?method=findOrders&state=3");
		return null;
	}	
}
