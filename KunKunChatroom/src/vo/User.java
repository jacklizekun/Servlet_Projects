package vo;

import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
/**
 * 用户
 * @version 1.0
 * @author 李泽坤
 *
 */
public class User implements HttpSessionBindingListener {
	//用户id
	private int id;
	//用户姓名
	private String username;
	//用户密码
	private String password;
	//用户是否是管理员或者普通用户
	private String type;
	//重新复写hashCode计算方法
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	//判断是否是同一个用户
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	//用户入场效果
	//session监听器
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("进入了坤坤聊天室");
		HttpSession session = event.getSession();
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) session.getServletContext().getAttribute("userMap");
		userMap.put(this, session);
	}

	// 退场
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("退出了坤坤聊天室");
		HttpSession session = event.getSession();
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) session.getServletContext().getAttribute("userMap");
		userMap.remove(this);
	}

}