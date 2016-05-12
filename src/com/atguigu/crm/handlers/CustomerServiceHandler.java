package com.atguigu.crm.handlers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.CustomerServiceService;
import com.atguigu.crm.services.UserService;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.CustomerService;
import com.atuigu.crm.entity.User;
import com.atuigu.crm.orm.Page;

/**
 * 
 * @author 杨博
 * @date 2016年4月1日
 */
@Controller
@RequestMapping("/service")
public class CustomerServiceHandler {
	
	@Autowired
	private CustomerServiceService customerServiceService;
	
	@Autowired
	private UserService userService;

	/**
	 * 查询
	 * @param pageNoStr
	 * @param request
	 * @param map
	 * @return
	 * @author Mars Lou
	 * @date 2016年4月2日
	 */
	@RequestMapping(value="/allot/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
			HttpServletRequest request,
			Map<String,Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		//获取查询条件的请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//把查询条件的 params 转为查询的字符串
		String queryString = PageUtils.encodeParamsToQueryString(params);
		//把查询条件的字符串放入到 request 中
		map.put("queryString", queryString);
		
		List<User> users = userService.getUserList();
		map.put("users", users);
		
		Page<CustomerService> page = customerServiceService.getPage(pageNo, params);
		map.put("page", page);
		return "/service/allot/list";
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 * @author Mars Lou
	 * @date 2016年4月2日
	 */
	@ResponseBody
	@RequestMapping(value="/delete" ,method=RequestMethod.DELETE)
	public String delete(@RequestParam Integer id){
		
		customerServiceService.delete(id);
		
		return "1";
	}
	
	/**
	 * 分配服务
	 * @return
	 * @author Mars Lou
	 * @date 2016年4月2日
	 */
	@RequestMapping(value="/allot")
	public String allot(){
		return "";
	}
	
	/**
	 * Archive
	 * @author y_y
	 * @param request
	 * @param pageNoStr
	 * @param map
	 * @return
	 */
	@RequestMapping("/archive/list")
	public String archiveList(HttpServletRequest request, 
			@RequestParam(value="pageNo",required=false) String pageNoStr, Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = PageUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		Page<CustomerService> page = customerServiceService.getCusertomerServiceForArtchive(pageNo,params);
		map.put("page", page);
		return "service/archive/list";
	}
	
	/**
	 * Archive
	 * @author y_y
	 *
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/archive/{id}")
	public String archiveMore(@PathVariable("id") Integer id, Map<String,Object> map){
		CustomerService customerService = customerServiceService.getCustomerServiceByIdForArtchive(id);
		map.put("service", customerService);
		return "service/archive/archive";
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 * @author 杨博
	 * @date 2016年4月1日
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String toInput(Map<String, Object> map) {
		map.put("customers", customerServiceService.getAllCustomer());
		map.put("services", customerServiceService.getAllCustomerService());
		map.put("customerService", new CustomerService());
		return "service/input";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(CustomerService customerService, RedirectAttributes attributes) {
		customerServiceService.saveCustomerService(customerService);
		attributes.addFlashAttribute("message", "保存成功!");
		return "redirect:/service/create";
	}
	
	@RequestMapping(value="/feedback/list",method=RequestMethod.GET)
	public String listForFeedback(HttpServletRequest request, 
			@RequestParam(value="pageNo",required=false) String pageNoStr, Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		String queryString = encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		Page<CustomerService> page = customerServiceService.getPageForFeedback(pageNo, params);
		map.put("page", page);
		return "service/feedback/list";
	}
	
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
