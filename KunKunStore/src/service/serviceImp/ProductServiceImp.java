package service.serviceImp;

import java.util.List;

import dao.ProductDao;
import dao.daoImp.ProductDaoImp;
import domain.BeanFactory;
import domain.PageModel;
import domain.Product;
import service.ProductService;


public class ProductServiceImp implements ProductService {

	@Override
	public void saveProduct(Product product) throws Exception {
		ProductDao.saveProduct(product);
		
	}

	ProductDao ProductDao=(ProductDao)BeanFactory.createObject("ProductDao");
	
	@Override
	public Product findProductByPid(String pid) throws Exception {
		return ProductDao.findProductByPid(pid);
		
	}

	@Override
	public List<Product> findHots() throws Exception {
		return ProductDao.findHots();
	}

	@Override
	public List<Product> findNews() throws Exception {
		return ProductDao.findNews();
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {

		int totalRecords=ProductDao.findTotalRecords(cid);
		PageModel pm=new PageModel(curNum,totalRecords,12);
		List list=ProductDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}

	@Override
	public PageModel findAllProductsWithPage(int curNum) throws Exception {
		int totalRecords=ProductDao.findTotalRecords();
		PageModel pm=new PageModel(curNum,totalRecords,5);
		List<Product> list=ProductDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;
	}

}
