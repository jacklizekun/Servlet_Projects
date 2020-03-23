package web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import domain.Category;
import domain.PageModel;
import domain.Product;
import service.CategoryService;
import service.ProductService;
import service.serviceImp.CategoryServiceImp;
import service.serviceImp.ProductServiceImp;
import utils.UUIDUtils;
import utils.UploadUtils;
import web.base.BaseServlet;

public class AdminProductServlet extends BaseServlet {
	public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int curNum=Integer.parseInt(req.getParameter("num"));
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findAllProductsWithPage(curNum);
		req.setAttribute("page", pm);
		return "/admin/product/list.jsp";
	}
	
	public String addProductUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list = CategoryService.getAllCats();
		req.setAttribute("allCats", list);
		return "/admin/product/add.jsp";
	}

	public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		Product product=new Product();
		try {
			//文件
			DiskFileItemFactory fac=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(fac);
			List<FileItem> list=upload.parseRequest(req);
			for (FileItem item : list) {
				if(item.isFormField()){
					map.put(item.getFieldName(), item.getString("utf-8"));
				}else{
					String oldFileName=item.getName();
					String newFileName=UploadUtils.getUUIDName(oldFileName);
					InputStream is=item.getInputStream();
					//webapps\KunKunStore\products\item
					String realPath=getServletContext().getRealPath("/products/item/");
					String dir=UploadUtils.getDir(newFileName); 
					String path=realPath+dir; 
					File newDir=new File(path);
					if(!newDir.exists()){
						newDir.mkdirs();
					}
					File finalFile=new File(newDir,newFileName);
					if(!finalFile.exists()){
						finalFile.createNewFile();
					}
	
					OutputStream os=new FileOutputStream(finalFile);
					IOUtils.copy(is, os);
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
					map.put("pimage", "/products/3/"+dir+"/"+newFileName);
				}
			}

			BeanUtils.populate(product, map);
			product.setPid(UUIDUtils.getId());
			product.setPdate(new Date());
			product.setPflag(0);
			ProductService ProductService=new ProductServiceImp();
			ProductService.saveProduct(product);
			resp.sendRedirect("${pageContext.request.contextPath}/AdminProductServlet?method=findAllProductsWithPage&num=1");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
