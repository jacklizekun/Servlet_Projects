package service.serviceImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.OrderDao;
import dao.daoImp.OrderDaoImp;
import domain.Order;
import domain.OrderItem;
import domain.PageModel;
import domain.User;
import service.OrderService;
import utils.JDBCUtils;

public class OrderServiceImp implements OrderService {
	
	OrderDao orderDao=new OrderDaoImp();

	@Override
	public List<Order> findAllOrders() throws Exception {
		return orderDao.findAllOrders();
	}

	@Override
	public List<Order> findAllOrders(String st) throws Exception {
		return orderDao.findAllOrders(st);
	}

	@Override
	public void saveOrder(Order order) throws SQLException {
		Connection conn=null;
		try {

			conn=JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			orderDao.saveOrder(conn,order);
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn,item);	
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		int totalRecords=orderDao.getTotalRecords(user);
		PageModel pm=new PageModel(curNum, totalRecords, 3);
		List list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);
		
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		orderDao.updateOrder(order);
		
	}
}