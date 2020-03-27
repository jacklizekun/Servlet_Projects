package admin.admin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import admin.admin.domain.Admin;
import util.jdbc.TxQueryRunner;

/**
 * 管理员模块的持久层
 * @author 李泽坤
 *
 */
public class AdminDao {
	private QueryRunner qr = new TxQueryRunner();
	
	//通过管理员登录名和登录密码查询
	public Admin find(String adminname, String adminpwd) throws SQLException {
		String sql = "select * from t_admin where adminname=? and adminpwd=?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminname, adminpwd);
	}
}
