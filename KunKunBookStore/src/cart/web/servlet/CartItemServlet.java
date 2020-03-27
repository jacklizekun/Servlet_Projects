package cart.web.servlet;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.commons.CommonUtils;
import book.domain.Book;
import cart.domain.CartItem;
import cart.service.CartItemService;
import user.domain.User;
import util.servlet.BaseServlet;
/**
 * 购物车的WEB层
 * @author 李泽坤
 *
 */
public class CartItemServlet extends BaseServlet {
	private CartItemService cartItemService = new CartItemService();
	// 加载多个CartItem
	public String loadCartItems(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//获取cartItemIds参数
		String cartItemIds = req.getParameter("cartItemIds");
		double total = Double.parseDouble(req.getParameter("total"));
		// 通过service得到List<CartItem>
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);
		//保存，然后转发到/cart/showitem.jsp
		req.setAttribute("cartItemList", cartItemList);
		req.setAttribute("total", total);
		req.setAttribute("cartItemIds", cartItemIds);
		return "f:/jsps/cart/showitem.jsp";
	}
	
	public String updateQuantity(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String cartItemId = req.getParameter("cartItemId");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		CartItem cartItem = cartItemService.updateQuantity(cartItemId, quantity);
		// 给客户端返回一个json对象
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"quantity\"").append(":").append(cartItem.getQuantity());
		sb.append(",");
		sb.append("\"subtotal\"").append(":").append(cartItem.getSubtotal());
		sb.append("}");

		resp.getWriter().print(sb);
		return null;
	}
	
	//批量删除功能
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String cartItemIds = req.getParameter("cartItemIds");
		cartItemService.batchDelete(cartItemIds);
		return myCart(req, resp);
	}

	//添加购物车条目
	public String add(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//封装表单数据到CartItem(bid, quantity)
		Map map = req.getParameterMap();
		CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		User user = (User)req.getSession().getAttribute("sessionUser");
		cartItem.setBook(book);
		cartItem.setUser(user);
		//调用service完成添加
		cartItemService.add(cartItem);
		//查询出当前用户的所有条目，转发到list.jsp显示
		return myCart(req, resp);
	}
	
	//我的购物车
	public String myCart(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// 得到uid
		User user = (User)req.getSession().getAttribute("sessionUser");
		String uid = user.getUid();
		//通过service得到当前用户的所有购物车条目
		List<CartItem> cartItemLIst = cartItemService.myCart(uid);
		//保存起来，转发到/cart/list.jsp
		req.setAttribute("cartItemList", cartItemLIst);
		return "f:/jsps/cart/list.jsp";
	}
}