package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atuigu.crm.entity.Order;

public interface OrderMapper {
 
	List<Order> getHistoryOrderListByCustomer(Map<String, Object> map);

	long getTotalElements(@Param("id") Integer id);
	
	Order getOrderById(@Param("id")Integer id);
}
