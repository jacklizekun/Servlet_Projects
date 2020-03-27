package admin.admin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import admin.admin.domain.Admin;
import admin.admin.service.AdminService;
import util.commons.CommonUtils;
import util.servlet.BaseServlet;

/**
 *管理登陆WEB层
 * @author 李泽坤
 *
 */
public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();
	//登录功能
	public String login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//封装表单数据到Admin
		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		Admin admin = adminService.login(form);
		if(admin == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			return "/adminjsps/login.jsp";
		}
		req.getSession().setAttribute("admin", admin);
		return "r:/adminjsps/admin/index.jsp";
	}
}
