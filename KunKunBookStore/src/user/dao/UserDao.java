package user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import user.domain.User;
import util.jdbc.TxQueryRunner;

/***
 * 用户模块持久层
 * @author 李泽坤
 *
 */
public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	//uid 和 password查询
	public boolean findByUidAndPassword(String uid, String password) throws SQLException {
		String sql = "select count(*) from t_user where uid=? and loginpass=?";
		//查询条数
		Number number = (Number)qr.query(sql, new ScalarHandler(), uid, password);
		//存在用户并且密码正确
		return number.intValue() > 0;
	}
	
	//uid修改密码
	public void updatePassword(String uid, String password) throws SQLException {
		String sql = "update t_user set loginpass=? where uid=?";
		//更新数据库
		qr.update(sql, password, uid);
	}
	
	//用户名和密码查询
	public User findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException {
		String sql = "select * from t_user where loginname=? and loginpass=?";
		//若用户名和密码正确，返回User对象
		return qr.query(sql, new BeanHandler<User>(User.class), loginname, loginpass);
	}
	
	//通过激活码查询用户是否存在并且返回User对象
	public User findByCode(String code) throws SQLException {
		String sql = "select * from t_user where activationCode=?";
		return qr.query(sql, new BeanHandler<User>(User.class), code);
	}
	
	//修改用户激活状态，参数uid和状态码
	public void updateStatus(String uid, boolean status) throws SQLException {
		String sql = "update t_user set status=? where uid=?";
		qr.update(sql, status, uid);
	}
	
	//检查用户名是否注册，返0/1
	public boolean ajaxValidateLoginname(String loginname) throws SQLException {
		String sql = "select count(1) from t_user where loginname=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), loginname);
		return number.intValue() == 0;
	}
	
	//检查邮箱是否注册
	public boolean ajaxValidateEmail(String email) throws SQLException {
		String sql = "select count(1) from t_user where email=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), email);
		return number.intValue() == 0;
	}
	//添加用户，传入User对象并且解析用户参数
	public void add(User user) throws SQLException {
		String sql = "insert into t_user values(?,?,?,?,?,?)";
		Object[] params = {user.getUid(), user.getLoginname(), user.getLoginpass(),
				user.getEmail(), user.isStatus(), user.getActivationCode()};
		qr.update(sql, params);
	}
}
