package admin.admin.service;

import java.sql.SQLException;

import admin.admin.dao.AdminDao;
import admin.admin.domain.Admin;
/**
 * 管理员模块的业务层
 * @author 李泽坤
 *
 */
public class AdminService {
	private AdminDao adminDao = new AdminDao();
	
	// 登录功能
	public Admin login(Admin admin) {
		try {
			return adminDao.find(admin.getAdminname(), admin.getAdminpwd());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
