package dao.daoImp;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.OrderDao;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

	@Override
	public List<Order> findAllOrders() throws Exception {
		String sql="select * from orders";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class));
		
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		String sql="UPDATE orders SET ordertime=? ,total=? ,state= ?, address=?,NAME=?, telephone =? WHERE oid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
		qr.update(sql,params);
		
	}

	@Override
	public List<Order> findAllOrders(String st) throws Exception {
		String sql="select * from orders where state= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class),st);
	}

	@Override
	public List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
		String sql="select * from orders where uid=? limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);
		for (Order order : list) {
			String oid=order.getOid();
			sql="select * from orderItem o ,product p where o.pid=p.pid and oid=?";
			List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
			for (Map<String, Object> map : list02) {
				OrderItem orderItem=new OrderItem();
				Product product=new Product();
				DateConverter dt = new DateConverter();
				dt.setPattern("yyyy-MM-dd");
				ConvertUtils.register(dt, java.util.Date.class);
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);
				orderItem.setProduct(product);
				order.getList().add(orderItem);
				
			}
		}
		return list;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		String sql="select * from orders where oid= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		sql="select * from orderitem o, product p where o.pid=p.pid and oid=?";
		List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
		for (Map<String, Object> map : list02) {
			OrderItem orderItem=new OrderItem();
			Product product=new Product();
			DateConverter dt = new DateConverter();
			dt.setPattern("yyyy-MM-dd");
			ConvertUtils.register(dt, java.util.Date.class);
			BeanUtils.populate(orderItem, map);
			BeanUtils.populate(product, map);
			orderItem.setProduct(product);
			order.getList().add(orderItem);
		}
		return order;
	}

	@Override
	public int getTotalRecords(User user) throws Exception {
		String sql="select count(*) from orders where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return num.intValue();
	}

	@Override
	public void saveOrder(Connection conn, Order order) throws Exception {
		String sql="INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner();
		Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
		qr.update(conn,sql,params);
	}

	@Override
	public void saveOrderItem(Connection conn, OrderItem item) throws Exception {
		String sql="INSERT INTO orderitem VALUES(?,?,?,?,?)";
		QueryRunner qr=new QueryRunner();
		Object[] params={item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid()};
		qr.update(conn,sql,params);
	}

}
