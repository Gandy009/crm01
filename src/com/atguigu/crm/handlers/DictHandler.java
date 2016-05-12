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

import com.atguigu.crm.services.DictService;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.Dict;
import com.atuigu.crm.orm.Page;
/**
 * 
 * @author Mars Lou
 * @date 2016年4月1日
 */
@Controller
@RequestMapping("/dict") 
public class DictHandler {
	
	@Autowired
	private DictService dictService;
	
	
	/**
	 * 查询
	 * @param request
	 * @param pageNoStr
	 * @param map
	 * @return
	 * @author Mars Lou
	 * @date 2016年4月1日
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request,
			@RequestParam(value="pageNo",required=false) String pageNoStr,
			Map<String, Object> map){
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		//获取查询条件的请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//如何能保证在分页时可以携带查询条件. 
		//把查询条件的 params 转为查询的字符串
		String queryString = PageUtils.encodeParamsToQueryString(params);
		
		//把查询条件的字符串放入到 request 中
		map.put("queryString", queryString);
		
		Page<Dict> page=dictService.getPages(pageNo,params);
		map.put("page", page);
		return "/dict/list";
	}
	
	
	/**
	 * 转到Input
	 * @param map
	 * @return
	 * @author Mars Lou
	 * @date 2016年4月1日
	 */
	@RequestMapping(value="/create")
	public String create(@RequestParam(required=false) Integer id,Map<String, Object> map){
		
		Dict dict=null;
		if(null!=id){
			dict=dictService.getDictById(id);			
		}else{			
			dict=new Dict();
		}
		map.put("dict", dict);
		
		return "/dict/input";
	}
	
	/**
	 * 新增
	 * @param dict
	 * @return
	 * @author Mars Lou
	 * @date 2016年4月1日
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String save(Dict dict,RedirectAttributes attributes){
		
		dictService.save(dict);
		attributes.addFlashAttribute("message", "增加联系人成功!");	
		return "redirect:/dict/list";
	}
	/**
	 * 删除的Ajax
	 * @param id
	 * @return
	 * @author Mars Lou
	 * @date 2016年4月1日
	 */
	@ResponseBody
	@RequestMapping(value="delete",method=RequestMethod.DELETE)
	public String delete(Integer id){
		dictService.delete(id);
		return "1";
	}
	
	/**
	 * 修改
	 * @param dict
	 * @param attributes
	 * @return
	 * @author Mars Lou
	 * @date 2016年4月1日
	 */
	@RequestMapping(value="/create",method=RequestMethod.PUT)
	public String update(Dict dict,RedirectAttributes attributes){
		
		dictService.update(dict);
		attributes.addFlashAttribute("message", "更新联系人成功!");	
		return "redirect:/dict/list";
	}
	

}
