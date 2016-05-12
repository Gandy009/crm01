package com.atguigu.crm.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mappers.OrderItemMapper;
import com.atuigu.crm.entity.OrderItem;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	public Set<OrderItem> getOrderItemByOrder(Integer orderId) {
		
		return orderItemMapper.getOrderItemByOrder(orderId);
		
	}

	
	
}
