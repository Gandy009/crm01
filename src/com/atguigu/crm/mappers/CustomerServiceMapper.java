package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atuigu.crm.entity.Customer;
import com.atuigu.crm.entity.CustomerService;
import com.atuigu.crm.entity.User;

public interface CustomerServiceMapper {

	List<CustomerService> getAllCustomerServices();

	void saveCustomerService(CustomerService customerService);

	long getTotalElementsForFeedback(Map<String, Object> params);

	List<CustomerService> getContentForFeedback(Map<String, Object> params);
	
	long getTotalElement(Map<String, Object> mybatisParams);

	List<CustomerService> getContent(Map<String, Object> mybatisParams);

	void delete(Integer id);
	
	int getTotalCustomerForArtchive(Map<String, Object> mybatisParams);

	List<CustomerService> getCusertomerServiceForArtchive(Map<String, Object> mybatisParams);

	CustomerService getCustomerServiceByIdForArtchive(@Param("id")Integer id);

	Customer getCustomerById(Long id);
	
	User getCreatedUserById(Long id);
}
