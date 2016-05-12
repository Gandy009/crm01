package com.atguigu.crm.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atuigu.crm.entity.Storage;

/**
 * 库存管理handler
 * @author menjinqiu
 *
 */
@Controller
@RequestMapping("/storage")
public class StorageHandler{

	/**
	 * 获取库存列表
	 * @return
	 */
	@RequestMapping(value = "service", method = RequestMethod.GET)
	public String getStorageList(){
		return "storage/list";
	}
	
	/**
	 * 转到库存添加页面
	 * @return
	 */
	@RequestMapping(value = "create" ,method = RequestMethod.GET)
	public String storageCreat(Storage storage){
		
		return "storage/input";
	}
	
}
