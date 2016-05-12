package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.atuigu.crm.entity.Contact;

public interface ContactMapper {
	
	List<Contact> getAllManagers();

	long getTotalElements(Integer id);
	
	List<Contact> getContent(Map<String, Object> params);
	
	@SelectKey(before=true, keyColumn="id", keyProperty="id", resultType=Long.class, 
			statement="SELECT crm_seq.nextval FROM dual")
	@Insert("INSERT INTO contacts (id,memo, mobile, name, position,sex,tel,customer_id) "
			+ "VALUES(#{id},#{memo}, #{mobile}, #{name}, #{position},#{sex}, #{tel}, #{customer.id})")
	void save(Contact contact);
	void save2(Contact contact);
	void delete(Integer id);

	Contact getContactById(Integer id);

	void update(Contact contact);

}
