package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.ProductService;
import com.atuigu.crm.entity.Product;
import com.atuigu.crm.orm.Page;

/**
 * 产品信息的请求处理
 * @author xiaoqing
 *
 */
@Controller
@RequestMapping("/product")
public class ProductHandler {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 根据产品信息 ID 删除一条数据
	 * @param id
	 * @param attributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public String delete(@RequestParam("id") Integer id,
						  RedirectAttributes attributes) {
		productService.delete(id);
		
		return "1";
	}
	
	/**
	 * 更新一条产品数据
	 * @param product
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.PUT)
	public String update(Product product,
					   RedirectAttributes attributes) {
		productService.update(product);
		
		attributes.addFlashAttribute("message", "更新成功！");
		
		return "redirect:/product/list";
	}
	
	/**
	 * 保存一条产品数据
	 * @param product
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String save(Product product,
					   RedirectAttributes attributes) {
		productService.save(product);
		
		attributes.addFlashAttribute("message", "保存成功！");
		
		return "redirect:/product/list";
	}
	
	/**
	 * 去往新建产品信息页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/create")
	public String edit(@RequestParam(value="id", required=false) Integer id,
						Map<String, Object> map) {
		Product product = new Product();
		
		if(id != null) product = productService.get(id);
			
		map.put("product", product);
		
		return "product/input";
	}
	
	/**
	 * 去往分页显示页面
	 * @param pageNoStr
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
						HttpServletRequest request,
						Map<String,Object> map) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		//获取查询条件的请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//如何能保证在分页时可以携带查询条件. 
		//把查询条件的 params 转为查询的字符串
		String queryString = encodeParamsToQueryString(params);
		//把查询条件的字符串放入到 request 中
		map.put("queryString", queryString);
		
		Page<Product> page = productService.getPage(pageNo, params);
		map.put("page", page);
		
		return "product/list";
	}
	
	/**
	 * 将请求参数转换成字符串，在页面跳转时保留查询条件
	 * @param params
	 * @return
	 */
	private String encodeParamsToQueryString(Map<String, Object> params) {
	StringBuilder queryString = new StringBuilder();
	
	if(params != null
			&& params.size() > 0){
		for(Map.Entry<String, Object> entry: params.entrySet()){
			String key = entry.getKey();
			Object val = entry.getValue();
			
			if("".equals(val)){
				continue;
			}
			
			queryString.append("search_")
			                  .append(key)
			                  .append("=")
			                  .append(val)
			                  .append("&");
		}
		
		if(queryString.length() > 0){
			queryString.replace(queryString.length() - 1, queryString.length(), "");
		}
	}
	
	return queryString.toString();
}
	
}
