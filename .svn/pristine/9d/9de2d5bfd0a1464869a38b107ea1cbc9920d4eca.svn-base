package com.atguigu.crm.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mappers.CustomerActivityMapper;
import com.atuigu.crm.entity.CustomerActivity;
import com.atuigu.crm.orm.Page;

/**
 * 关于交往记录的逻辑业务处理
 * @author xiaoqing
 *
 */
@Service
public class CustomerActivityService {
	
	@Autowired
	private CustomerActivityMapper customerActivityMapper;
	
	/**
	 * 分页的逻辑业务
	 * @param pageNo
	 * @param id
	 * @return
	 */
	public Page<CustomerActivity> getPage(int pageNo, int id) {
		Page<CustomerActivity> page = new Page<>();
		
		page.setPageNo(pageNo);
		
		long totalElements = customerActivityMapper.getTotalElements(id);
		page.setTotalElements((int)totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		List<CustomerActivity> content = customerActivityMapper.getContent(id,firstIndex,endIndex);
		page.setContent(content);
		
		return page;
	}
	
	/**
	 * 保存的逻辑业务
	 * @param customerActivity
	 */
	public void save(CustomerActivity customerActivity) {
		customerActivityMapper.save(customerActivity);
	}
	
	/**
	 * 查询的逻辑业务
	 * @param id
	 * @return
	 */
	public CustomerActivity get(int id) {
		
		return customerActivityMapper.get(id);
	}
	
	/**
	 * 更新的逻辑业务
	 * @param activity
	 */
	public void update(CustomerActivity activity) {
		
		customerActivityMapper.update(activity);
	}
	
	/**
	 * 删除的逻辑业务
	 * @param id
	 */
	public void delete(int id) {
		
		customerActivityMapper.delete(id);
	}

}
