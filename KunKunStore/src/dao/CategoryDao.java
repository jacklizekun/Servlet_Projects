package dao;

import java.util.List;

import domain.Category;

public interface CategoryDao {

	List<Category> getAllCats()throws Exception;

	void addCategory(Category c)throws Exception;

}
