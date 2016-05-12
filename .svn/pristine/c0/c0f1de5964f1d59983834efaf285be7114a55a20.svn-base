package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.atuigu.crm.entity.Contact;
import com.atuigu.crm.entity.Customer;
import com.atuigu.crm.entity.CustomerDrain;

public interface CustomerDrainMapper {

	@Update("{call check_drain}")
	void add();

	long getTotalElements(Map<String, Object> params);

	List<CustomerDrain> getContent(Map<String, Object> params);
	
	Customer getCustomerById(Long id);
	
	List<Contact> getContactById(Long customerId);

	int updateState(Integer id);

	CustomerDrain getDrainById(Integer drainId);

	void updateDelay(CustomerDrain drain);

	void updateReason(CustomerDrain drain);

	void updateDelay2(CustomerDrain drain);
	
}
