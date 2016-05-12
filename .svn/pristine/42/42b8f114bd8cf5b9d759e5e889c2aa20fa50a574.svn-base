package com.atguigu.crm.handlers;

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

import com.atguigu.crm.services.CustomerDrainService;
import com.atuigu.crm.entity.CustomerDrain;
import com.atuigu.crm.orm.Page;

/**
 * 
 * @author 杨博
 * @date 2016年4月1日
 */
@Controller
@RequestMapping("/drain")
public class CustomerDrainHandler {
	
	@Autowired
	private CustomerDrainService customerDrainService;
	
	/**
	 * 
	 * @param id
	 * @param attributes
	 * @param confirm
	 * @return
	 * @author 杨博
	 * @date 2016年4月1日
	 */
	@RequestMapping(value="/confirm",method=RequestMethod.PUT)
	public String confirm(@RequestParam(value="id") Integer id, RedirectAttributes attributes,@RequestParam(value="reason") String reason) {
		CustomerDrain drain = customerDrainService.get(id);
		drain.setDelay(reason);
		customerDrainService.updateReason(drain);
		attributes.addFlashAttribute("message", "保存成功!");
		return "redirect:/drain/list";
	}
	
	@RequestMapping(value="/toConfirm/{drainId}",method=RequestMethod.GET)
	public String toConfirm(@PathVariable("drainId") Integer id, Map<String, Object> map) {
		CustomerDrain drain = customerDrainService.get(id);
		customerDrainService.updateDelay2(drain);
		map.put("drain", drain);
		return "drain/confirm";
	}
	
	/**
	 * 
	 * @param id
	 * @param attributes
	 * @param delay
	 * @return
	 * @author 杨博
	 * @date 2016年4月1日
	 */
	@RequestMapping(value="/save",method=RequestMethod.PUT)
	public String save(@RequestParam(value="id") Integer id, RedirectAttributes attributes,@RequestParam(value="delay") String delay) {
		StringBuilder builder = new StringBuilder();
		CustomerDrain drain = customerDrainService.get(id);
		String delay2 = drain.getDelay();
		builder.append(delay2).append("`").append(delay);
		drain.setDelay(builder.toString());
		customerDrainService.updateDelay(drain);
		attributes.addFlashAttribute("message", "追加暂缓措施成功!");
		return "redirect:/drain/delay/"+id;
	}
	
	/**
	 * 
	 * @param id
	 * @param map
	 * @return
	 * @author 杨博
	 * @date 2016年4月1日
	 */
	@RequestMapping(value="/delay/{drainId}",method=RequestMethod.GET)
	public String delay(@PathVariable("drainId") Integer id, Map<String, Object> map) {
		map.put("drain", customerDrainService.get(id));
		return "drain/delay";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @author 杨博
	 * @date 2016年4月1日
	 */
	@ResponseBody
	@RequestMapping(value="/updateState/{drainId}",method=RequestMethod.PUT)
	public String drainConfirm(@PathVariable("drainId") Integer id) {
		return customerDrainService.updateState(id);
	}

	/**
	 * 
	 * @param request
	 * @param pageNoStr
	 * @param map
	 * @return
	 * @author 杨博
	 * @date 2016年4月1日
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, 
			@RequestParam(value="pageNo",required=false) String pageNoStr, Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		String queryString = encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		Page<CustomerDrain> page = customerDrainService.getPage(pageNo, params);
		map.put("page", page);
		return "drain/list";
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
