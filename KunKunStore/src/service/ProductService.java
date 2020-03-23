package service;

import java.util.List;

import domain.PageModel;
import domain.Product;

public interface ProductService {

	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;

	PageModel findAllProductsWithPage(int curNum)throws Exception;

	void saveProduct(Product product)throws Exception;;

}
