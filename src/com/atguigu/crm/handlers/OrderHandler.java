package com.atguigu.crm.handlers;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.services.OrderItemService;
import com.atguigu.crm.services.OrderService;
import com.atuigu.crm.entity.Order;
import com.atuigu.crm.entity.OrderItem;
import com.atuigu.crm.orm.Page;

@RequestMapping("/order")
@Controller
public class OrderHandler {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	/**
	 * 订单分页查询
	 * @author y_y
	 *
	 * @param id
	 * @param pageNoStr
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String getHistoryOrderListByCustomer(@RequestParam(value="customerid") Integer id ,
			@RequestParam(value="pageNo",required=false) String pageNoStr,
			Map<String,Object> map){
		if(null!=id){
			
		Page<Order> page = orderService.getHistoryOrderListByCustomer(id,pageNoStr);
		map.put("queryString","&customerid="+id);
		map.put("page",page);
		
		}
		return "order/list";
	}
	
	
	@RequestMapping(value="/details")
	public String getOrderDetailById(@RequestParam(value="id") Integer id,Map<String,Object> map){
		Order order = orderService.getOrderById(id);
		map.put("order", order);
		return "order/details";
	}
	
}
