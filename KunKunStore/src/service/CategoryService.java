package service;

import java.util.List;

import domain.Category;

public interface CategoryService {

	List<Category> getAllCats()throws Exception;

	void addCategory(Category c)throws Exception;

}
