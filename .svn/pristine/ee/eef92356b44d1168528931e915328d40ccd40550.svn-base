package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atuigu.crm.entity.Contact;
import com.atuigu.crm.entity.Customer;
import com.atuigu.crm.entity.SalesChance;

public interface SalesChanceMapper {

	void update(SalesChance chance);
	
	void updateStopPlan(Integer chanceId);
	
	//模块5---------------------------------------------------------
	void updateSalesChanceFinishPlan(Integer chanceId);
	
	void insertCusomersFinishPlan(Customer customer);
	
	void insertContactsFinishPlan(Contact contact);
	
	List<Customer> getCustomerById(@Param("id") Integer chanceId);
	
	SalesChance getContactById(@Param("id") Integer chanceId);
	
	//--------------------------------------
	@Select("SELECT s.id, source, cust_name, rate, title, contact, contact_tel, s.description, created_user_id as \"createBy.id\", u.name as \"createBy.name\", r.name as \"createBy.role.name\", create_date ,designee.id as\"designee.id\",designee.name as \"designee.name\",status"
			+ " FROM sales_chances s "
			+ " LEFT OUTER JOIN users u "
			+ " ON s.created_user_id = u.id "
			+ " LEFT OUTER JOIN roles r "
			+ " ON u.role_id = r.id "
			+ " LEFT OUTER JOIN users designee"
			+ " ON s.designee_id = designee.id"
			+ " WHERE s.id = #{id}")
	SalesChance get(@Param("id") Integer id);
	
	@Delete("DELETE FROM sales_chances WHERE id = #{id}")
	void delete(@Param("id") Integer id);
	
	@Insert("INSERT INTO sales_chances(id, source, cust_name, rate, title, contact, contact_tel, description, created_user_id, create_date, status) "
			+ "VALUES(crm_seq.nextval, #{source}, #{custName}, #{rate}, #{title}, #{contact}, #{contactTel}, #{description}, #{createBy.id}, #{createDate}, 1)")
	void save(SalesChance salesChance);
	
	List<SalesChance> getContent2(Map<String, Object> params);
	
	long getTotalElements2(Map<String, Object> params);
	
	@Select("SELECT count(id) FROM sales_chances")
	long getTotalElements();
	
	//MySQL 分页使用 limit, Oracle 分页使用 rownum
	//因为 rownum 对 > 或 >= 不起作用, 所以必须使用子查询!  
	@Select("SELECT * FROM "
			+ "(SELECT rownum rn, id, cust_name, title, contact, contact_tel, create_date "
			+ "FROM sales_chances "
			+ "ORDER BY id) "
			+ "WHERE rn >= #{firstIndex} AND rn < #{endIndex}")
	List<SalesChance> getContent(@Param("firstIndex") int firstIndex, @Param("endIndex") int endIndex);
	
}
