package web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cart;
import domain.CartItem;
import domain.Product;
import service.ProductService;
import service.serviceImp.ProductServiceImp;
import web.base.BaseServlet;
/**
 * 
 * @author 李泽坤
 *
 */
public class CartServlet extends BaseServlet {
	

	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		if(null==cart){
			cart=new Cart();
			req.getSession().setAttribute("cart", cart);
		}
		String pid=req.getParameter("pid");
		int num=Integer.parseInt(req.getParameter("quantity"));
		ProductService ProductService=new ProductServiceImp();
		Product product=ProductService.findProductByPid(pid);	
		CartItem cartItem=new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);
		cart.addCartItemToCar(cartItem);
		resp.sendRedirect("/KunKunStore/jsp/cart.jsp");
		return  null;
	}
	

	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String pid=req.getParameter("id");
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		cart.removeCartItem(pid);
		resp.sendRedirect("/KunKunStore/jsp/cart.jsp");
		return null;
	}

	public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		cart.clearCart();
		resp.sendRedirect("/KunKunStore/jsp/cart.jsp");
		return null;
	}
}




