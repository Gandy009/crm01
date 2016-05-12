package com.atguigu.crm.handlers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.ContactService;
import com.atguigu.crm.services.CustomerInfoService;
import com.atguigu.crm.services.DictService;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.Customer;
import com.atuigu.crm.orm.Page;

@Controller
@RequestMapping("/customer")
public class CustomerInfoHandler {
	
	@Autowired
	private CustomerInfoService customerInfoService;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private ContactService contactService;
	
	@ResponseBody
	@RequestMapping(value="/updateState/{customerId}",method=RequestMethod.PUT)
	public String delete(@PathVariable("customerId") Integer id) {
		return customerInfoService.updateState(id);
	}
	
	@RequestMapping(value="/updateCustomer",method=RequestMethod.PUT)
	public String save(Customer customer, RedirectAttributes attributes){
		customerInfoService.updateCustomer(customer);
		attributes.addFlashAttribute("message", "修改顾客成功!");
		return "redirect:/customer/list";
	}

	@RequestMapping(value="/edit/{customerId}",method=RequestMethod.GET)
	public String input(@PathVariable("customerId") Integer id,Map<String, Object> map){
		map.put("regions", dictService.getRegions());
		map.put("levels", dictService.getLevels());
		map.put("satisfies", dictService.getSatisfies());
		map.put("credits", dictService.getCredits());
		map.put("managers", contactService.getAllManagers());
		map.put("customer", customerInfoService.get(id));
		return "customer/input";
	}
	
	@ModelAttribute
	public void getCustomerById(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		if (id!=null) {
			Customer customer = customerInfoService.get(id);
			map.put("customer", customer);
		}
	}
	/**
	 * 分页查询
	 * @param request
	 * @param pageNoStr
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/list")
	public String customerList(HttpServletRequest request, 
			@RequestParam(value="pageNo",required=false) String pageNoStr, Map<String, Object> map){
		int pageNo = 1;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		//获取查询条件的请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//如何能保证在分页时可以携带查询条件. 
		//把查询条件的 params 转为查询的字符串
		String queryString = PageUtils.encodeParamsToQueryString(params);
		
		//把查询条件的字符串放入到 request 中
		map.put("queryString", queryString);
		
		List<String> regions = customerInfoService.getRegions();
		List<String> levels = customerInfoService.getLevels();
		map.put("regions", regions);
		map.put("levels", levels);
		Page<Customer> page = customerInfoService.getPage(pageNo, params);
		map.put("page", page);
		return "customer/list";
	}
}
