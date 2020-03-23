package service.serviceImp;

import java.util.List;

import dao.CategoryDao;
import dao.daoImp.CategoryDaoImp;
import domain.BeanFactory;
import domain.Category;
import service.CategoryService;
import utils.JedisUtils;
import redis.clients.jedis.Jedis;

public class CategoryServiceImp implements CategoryService {

	CategoryDao CategoryDao=(CategoryDao)BeanFactory.createObject("CategoryDao");
	
	
	@Override
	public List<Category> getAllCats() throws Exception {
		return CategoryDao.getAllCats();
	}

	@Override
	public void addCategory(Category c) throws Exception {
		CategoryDao.addCategory(c);
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

}
