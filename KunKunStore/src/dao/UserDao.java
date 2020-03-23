package dao;

import java.sql.SQLException;

import domain.User;

public interface UserDao {

	void userRegist(User user)throws SQLException;

	User userActive(String code)throws SQLException;

	void updateUser(User user)throws SQLException;

	User userLogin(User user)throws SQLException;

}
