package service.serviceImp;

import java.sql.SQLException;

import dao.UserDao;
import dao.daoImp.UserDaoImp;
import domain.BeanFactory;
import domain.User;
import service.UserService;

public class UserServiceImp implements UserService {
	UserDao UserDao=(UserDao)BeanFactory.createObject("UserDao");
	
	
	@Override
	public void userRegist(User user) throws SQLException {
		UserDao.userRegist(user);
	}

	@Override
	public boolean userActive(String code) throws SQLException {
		User user=UserDao.userActive(code);
		if(null!=user){
			user.setState(1);
			user.setCode(null);
			UserDao.updateUser(user);
			return  true;
		}else{
			return false;
		}
	}

	@Override
	public User userLogin(User user) throws SQLException {

		User uu=UserDao.userLogin(user);
		if(null==uu){
			throw new RuntimeException("密码有误!");
		}else if(uu.getState()==0){
			throw new RuntimeException("用户未激活!");
		}else{
			return uu;
		}
	}

}
