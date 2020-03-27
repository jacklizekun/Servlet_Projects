package web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.User;
import service.CategoryService;
import service.UserService;
import service.serviceImp.CategoryServiceImp;
import service.serviceImp.UserServiceImp;
import utils.MailUtils;
import utils.MyBeanUtils;
import utils.UUIDUtils;
import web.base.BaseServlet;
/**
 *
 * @author 李泽坤
 *
 */
public class UserServlet extends BaseServlet {

	public String registUI(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	public String loginUI(HttpServletRequest request, HttpServletResponse response)throws Exception {
		return "/jsp/login.jsp";
	}

	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		MyBeanUtils.populate(user, map);
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		System.out.println(user);
		UserService UserService = new UserServiceImp();
		try {
			UserService.userRegist(user);
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "用户注册成功,请激活!");
		} catch (Exception e) {
			request.setAttribute("msg", "用户注册失败,请重新注册!");
		}
		return "/jsp/info.jsp";
	}

	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code=request.getParameter("code");
		UserService UserService=new UserServiceImp();
		boolean flag=UserService.userActive(code);
		if(flag==true){
			request.setAttribute("msg", "用户激活成功,请登录!");
			return "/jsp/login.jsp";
		}else{
			request.setAttribute("msg", "用户激活失败,请重新激活!");
			return  "/jsp/info.jsp";
		}
	}

	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		UserService UserService=new UserServiceImp();
		User user02=null;
		try {
			user02=UserService.userLogin(user);
			request.getSession().setAttribute("loginUser", user02);
			response.sendRedirect("/KunKunStore/index.jsp");
			return null;
		} catch (Exception e) {
			String msg=e.getMessage();
			System.out.println(msg);
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}
	
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.getSession().invalidate();
		  response.sendRedirect("/KunKunStore/index.jsp");
		  return null;	
	}
}
