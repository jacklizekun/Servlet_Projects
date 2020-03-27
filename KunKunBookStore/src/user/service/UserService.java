package user.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import util.commons.CommonUtils;
import user.dao.UserDao;
import user.domain.User;
import user.service.exception.UserException;
import util.mail.Mail;
import util.mail.MailUtils;

/**
 * 用户模块业务层
 * @author 李泽坤
 *
 */
public class UserService {
	//创建持久层实例
	private UserDao userDao = new UserDao();
	
	//修改密码，传入uid和新旧密码
	public void updatePassword(String uid, String newPass, String oldPass) throws UserException {

		try {
			//调用uid和密码查询用户是否存在
			boolean bool = userDao.findByUidAndPassword(uid, oldPass);
			if(!bool) 
			{//如果老密码错误，抛出自定义异常
				throw new UserException("坤坤提示:原密码错误！");
			}
			//老密码正确，用新密码和uid更新用户
			userDao.updatePassword(uid, newPass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//用户登陆流程，传入USER对象
	public User login(User user) {
		try {
			//调用传入的USER对象中的用户名和密码进行校验
			return userDao.findByLoginnameAndLoginpass(user.getLoginname(), user.getLoginpass());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//激活功能，传入激活码
	public void activatioin(String code) throws UserException {

		try {
			//调用持久层传入激活码code查询用户，返回查询到的USER对象
			User user = userDao.findByCode(code);
			//返回值无USER对象
			if(user == null) throw new UserException("无效的激活码！");
			//如果存在对应的USER对象，但是已经激活
			if(user.isStatus()) throw new UserException("您已经激活过了，不要二次激活！");
			//正常注册
			userDao.updateStatus(user.getUid(), true);//修改状态
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//ajax异步逻辑校验用户名，传入参数为用户名
	public boolean ajaxValidateLoginname(String loginname) {
		try {
			//返回查询条数0/1
			return userDao.ajaxValidateLoginname(loginname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//Email异步逻辑校验，传入邮箱
	public boolean ajaxValidateEmail(String email) {
		try {
			//返回0/1是否注册
			return userDao.ajaxValidateEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//注册
	public void regist(User user) {
		//补充页面不具有的属性
		user.setUid(CommonUtils.uuid());
		//设置状态未激活
		user.setStatus(false);
		//设置64位的激活码
		user.setActivationCode(CommonUtils.uuid() + CommonUtils.uuid());
		//数据库插入完成信息的用户对象
		try {
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		//获取属性配置文件
		Properties prop = new Properties();
		try {
			//Service类的类加载器获取src下邮箱配置文件，加载到属性中
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		//获取服务器主机名、登陆名和登陆密码
		String host = prop.getProperty("host");//服务器主机名
		String name = prop.getProperty("username");//登录名
		String pass = prop.getProperty("password");//登录密码
		//利用MailUtils获取邮箱建立的session
		Session session = MailUtils.createSession(host, name, pass);
		
		//创建mail对象并配置信息
		String from = prop.getProperty("from");
		//发送到用户的邮箱
		String to = user.getEmail();
		//发送的主题
		String subject = prop.getProperty("subject");
		// MessageForm.format方法会把第一个参数中的{0},使用第二个参数来替换。
		// 例如MessageFormat.format("你好{0}, 你{1}!", "张三", "11"); 返回“你好张三，11”
		//将激活码 插入邮件内容中
		String content = MessageFormat.format(prop.getProperty("content"), user.getActivationCode());
		Mail mail = new Mail(from, to, subject, content);
		//利用MailUtils，传入session连接和mail文件内容
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
