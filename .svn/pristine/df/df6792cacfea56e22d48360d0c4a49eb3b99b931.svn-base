package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mappers.OrderMapper;
import com.atuigu.crm.entity.Order;
import com.atuigu.crm.orm.Page;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 分页订单查询
	 * @author y_y
	 *
	 * @param id
	 * @param pageNoStr
	 * @return
	 */
	public Page<Order> getHistoryOrderListByCustomer(Integer id, String pageNoStr){
		
		long totalElements =(long)orderMapper.getTotalElements(id);
		
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(pageNoStr);
		}catch(Exception e){}
		
		Page<Order> page = new Page<Order>();
		page.setPageNo(pageNo);
		page.setPageSize(2);
		page.setTotalElements((int)totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String,Object> map= new HashMap<>();
		map.put("id", id);
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		
		List<Order> list = orderMapper.getHistoryOrderListByCustomer(map);
		
		page.setContent(list);
		
		return page;

	}

	public Order getOrderById(Integer id) {
		return orderMapper.getOrderById(id);
	}
	
}
