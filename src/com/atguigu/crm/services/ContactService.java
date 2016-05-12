package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.ContactMapper;
import com.atuigu.crm.entity.Contact;
import com.atuigu.crm.orm.Page;

@Service
public class ContactService {

	@Autowired
	private ContactMapper contactMapper;
	
	public List<Contact> getAllManagers() {
		List<Contact> contacts = contactMapper.getAllManagers();
		return contacts;
	}

	@Transactional
	public Page<Contact> getPages(Integer id, Integer pageNo) {
		
		Page<Contact> page=new Page<>();
		page.setPageNo(pageNo);
		
		//2. 查询 totalElements
		int totalElements = (int) contactMapper.getTotalElements(id);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		//firstIndex, endIndex,id
		Map<String, Object> params=new HashMap<>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		params.put("id", id);
		List<Contact> content = contactMapper.getContent(params);
		page.setContent(content);
				
		return page;
	}
/*
	public List<Contact> getManagersByCustomer(Long mgrId){
		Customer customer = new Customer();
		customer.setId(mgrId);
		
		return ((ContactRepository)repository).getContactByCustomer(customer);
	}
	
	@Override
	protected Map<String, SearchFilter> buildSearchFilters() {
		Customer customer = ContactController.customers.get();
		ContactController.customers.remove();
		
		Map<String, SearchFilter> map = new HashMap<String, SearchFilter>();
		map.put("customer", new SearchFilter("customer", Operator.EQ, customer)); 
		
		return map;
	}*/

	public void save(Contact contact) {

		contactMapper.save2(contact);
	}

	public void delete(Integer id) {
		
		contactMapper.delete(id);
		
	}

	public Contact getContactById(Integer id) {
		return contactMapper.getContactById(id) ;
	}

	public void update(Contact contact) {
		contactMapper.update(contact) ;
	}

	public int getContactNumByCustomerId(Integer customerId) {
		
		return (int) contactMapper.getTotalElements(customerId);
	}

}
