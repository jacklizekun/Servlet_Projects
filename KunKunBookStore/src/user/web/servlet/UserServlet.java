package user.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.commons.CommonUtils;
import user.domain.User;
import user.service.UserService;
import user.service.exception.UserException;
import util.servlet.BaseServlet;

/**
 * 用户模块WEB层
 * @author 李泽坤
 *激活可发送邮件
 */
public class UserServlet extends BaseServlet {
	//新建用户模块的业务层
	private UserService userService = new UserService();
	
	//ajax异步校验请求用户名是否注册，传入参数HttpServletRequest
	public String ajaxValidateLoginname(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//获取页面的请求参数位loginname登录名
		String loginname = req.getParameter("loginname");
		//通过service校验，传回布尔值类型
		boolean b = userService.ajaxValidateLoginname(loginname);
		//发送传递回页面
		resp.getWriter().print(b);
		return null;
	}
	
	//ajax异步校验邮箱是否注册，传入参数HttpServletRequest
	public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//获取请求数据中的参数email
		String email = req.getParameter("email");
		// 通过service得到校验结果
		boolean b = userService.ajaxValidateEmail(email);
		// 发给客户端
		resp.getWriter().print(b);
		return null;
	}
	
	//ajax异步检验 验证码是否正确校验
	public String ajaxValidateVerifyCode(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// 获取输入框中的验证码
		String verifyCode = req.getParameter("verifyCode");
		//获取session中图片的真实的校验码
		String vcode = (String) req.getSession().getAttribute("vCode");
		//忽略大小写比较
		boolean b = verifyCode.equalsIgnoreCase(vcode);
		//发送给客户端
		resp.getWriter().print(b);
		return null;
	}

	//注册，输入参数HttpServletRequest
	public String regist(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//利用CommonUtils将用户参数封装到用户对象中
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		//将session从req中取得，调用Servlet中方法
		Map<String,String> errors = validateRegist(formUser, req.getSession());
		if(errors.size() > 0) {
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			//转向regist.jsp中，注册出错
			return "f:/jsps/user/regist.jsp";
		}
		//注册
		userService.regist(formUser);
		//保存成功信息，转发到msg.jsp显示
		req.setAttribute("code", "success");
		req.setAttribute("msg", "注册功能，请马上到邮箱激活！");
		return "f:/jsps/msg.jsp";
	}
	
	//注册校验
	private Map<String,String> validateRegist(User formUser, HttpSession session) {
		//错误errors信息map表
		Map<String,String> errors = new HashMap<String,String>();

		String loginname = formUser.getLoginname();
		if(loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空！");
		} else if(loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "用户名长度必须在3~20之间！");
		} else if(!userService.ajaxValidateLoginname(loginname)) {
			errors.put("loginname", "用户名已被注册！");
		}
		
		//校验登录密码
		String loginpass = formUser.getLoginpass();
		if(loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空！");
		} else if(loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在3~20之间！");
		}
		
		// 确认密码校验
		String reloginpass = formUser.getReloginpass();
		if(reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpass", "确认密码不能为空！");
		} else if(!reloginpass.equals(loginpass)) {
			errors.put("reloginpass", "两次输入不一致！");
		}
		
		// 校验email
		String email = formUser.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空！");
		} else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "Email格式错误！");
		} else if(!userService.ajaxValidateEmail(email)) {
			errors.put("email", "Email已被注册！");
		}
		
		//验证码校验
		String verifyCode = formUser.getVerifyCode();
		String vcode = (String) session.getAttribute("vCode");
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if(!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "验证码错误！");
		}
		
		return errors;
	}
	
	//激活功能
	public String activation(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String code = req.getParameter("activationCode");
		try {
			//使用传入的激活码进行激活
			userService.activatioin(code);
			//激活成功。通知msg.jsp显示对号
			req.setAttribute("code", "success");
			req.setAttribute("msg", "恭喜，激活成功，请马上登录！");
			//捕捉自定义异常
		} catch (UserException e) {
			// 说明service抛出了异常
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("code", "error");//通知msg.jsp显示X
		}
		//跳转到信息页面
		return "f:/jsps/msg.jsp";
	}
	
	//修改新密码
	public String updatePassword(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//将请求的数据封装到user对象
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		//获取session中存储的用户信息
		User user = (User)req.getSession().getAttribute("sessionUser");
		// 如果用户没有登录，返回到登录页面，显示错误信息
		if(user == null) {
			req.setAttribute("msg", "您还没有登录！");
			return "f:/jsps/user/login.jsp";
		}
		
		try {
			//修改密码
			userService.updatePassword(user.getUid(), formUser.getNewpass(), formUser.getLoginpass());
			req.setAttribute("msg", "修改密码成功");
			req.setAttribute("code", "success");
			return "f:/jsps/msg.jsp";
			//抛出自定义异常
		} catch (UserException e) {
			//保存异常信息到request
			req.setAttribute("msg", e.getMessage());
			//为了回显
			req.setAttribute("user", formUser);
			return "f:/jsps/user/pwd.jsp";
		}
	}
	
	//退出
	public String quit(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//使session失效
		req.getSession().invalidate();
		//转向登陆页面
		return "r:/jsps/user/login.jsp";
	}
	
	//登陆
	public String login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//封装表单数据到user
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		//校验用户
		Map<String,String> errors = validateLogin(formUser, req.getSession());
		if(errors.size() > 0) {
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "f:/jsps/user/login.jsp";
		}
		//调用service的登陆
		User user = userService.login(formUser);
		//根据service返回的用户对象进行判断
		if(user == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			req.setAttribute("user", formUser);
			return "f:/jsps/user/login.jsp";
		} else {
			if(!user.isStatus()) {
				req.setAttribute("msg", "您还没有激活！");
				req.setAttribute("user", formUser);
				return "f:/jsps/user/login.jsp";				
			} else {
				// 保存用户到session
				req.getSession().setAttribute("sessionUser", user);
				// 获取用户名保存到cookie中
				String loginname = user.getLoginname();
				//中文编码
				loginname = URLEncoder.encode(loginname, "utf-8");
				//将用户名放入cookie中
				Cookie cookie = new Cookie("loginname", loginname);
				//cookie单位时间位秒
				cookie.setMaxAge(60 * 60 * 24 * 10);//保存10天
				//在response中添加cookie
				resp.addCookie(cookie);
				return "r:/index.jsp";//重定向到主页
			}
		}
	}
	
	//登录校验方法，内容等你自己来完成
	 
	private Map<String,String> validateLogin(User formUser, HttpSession session) {
		Map<String,String> errors = new HashMap<String,String>();
		return errors;
	}
}
