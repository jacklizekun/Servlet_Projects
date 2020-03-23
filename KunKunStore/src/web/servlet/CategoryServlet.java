package web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import service.CategoryService;
import service.serviceImp.CategoryServiceImp;
import utils.JedisUtils;
import web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {

	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Jedis jedis=JedisUtils.getJedis();
		String jsonStr=jedis.get("allCats");
		if(null==jsonStr||"".equals(jsonStr)){
			CategoryService CategoryService=new CategoryServiceImp();
			List<Category> list = CategoryService.getAllCats();
			jsonStr=JSONArray.fromObject(list).toString();
			System.out.println(jsonStr);
			jedis.set("allCats", jsonStr);
			System.out.println("redis缓存中没有数据");
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(jsonStr);			
		}else{
			System.out.println("redis缓存中有数据");
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(jsonStr);
		}
		
		JedisUtils.closeJedis(jedis);

		return null;
	}
}
