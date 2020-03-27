package dao;

import domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户持久层接口
 * @author 李泽坤
 *
 */
public interface UserDao {


    public List<User> findAll();

    User findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    void delete(int id);

    User findById(int i);

    void update(User user);

    //查询总记录数
    int findTotalCount(Map<String, String[]> condition);
    
    //分页查询每页记录
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
