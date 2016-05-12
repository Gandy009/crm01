package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atuigu.crm.entity.Customer;

public interface CustomerMapper {
	
	long getTotalCustomer(Map<String, Object> params);

	List<Customer> getCustomers(Map<String, Object> mybatisParams);

	Customer getCustomerById(Integer id);

	void updateCustomer(Customer customer);

	int updateState(Integer id);

	List<String> getRegions();

	List<String> getLevels();

}
