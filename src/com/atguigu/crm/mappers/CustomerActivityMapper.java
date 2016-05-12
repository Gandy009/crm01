package com.atguigu.crm.mappers;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.atuigu.crm.entity.CustomerActivity;

/**
 * 关于交往记录的数据库操作
 * @author xiaoqing
 *
 */
public interface CustomerActivityMapper {
	
	/**
	 * 查询交往记录的总数
	 * @param id
	 * @return
	 */
	@Select("SELECT COUNT(*) "
			+ "FROM customer_activities "
			+ "WHERE customer_id = #{id}")
	long getTotalElements(@Param("id") int id);
	
	/**
	 * 查询交往记录的所有数据集合
	 * @param id
	 * @param firstIndex
	 * @param endIndex
	 * @return
	 */
	@Select("SELECT * "
		  + "FROM (SELECT rownum rn,id,activity_date as \"date\",description,place,title,customer_id \"customer.id\" "
		  + "FROM customer_activities "
		  + "WHERE customer_id = #{id} "
		  + "ORDER BY id) "
		  + "WHERE rn >= #{firstIndex} AND rn < #{endIndex}")
	List<CustomerActivity> getContent(@Param("id") int id,@Param("firstIndex") int firstIndex,@Param("endIndex") int endIndex);
	
	/**
	 * 保存一条交往记录
	 * @param customerActivity
	 */
	@SelectKey(before=true,keyColumn="id",keyProperty="id",resultType=Long.class,statement="select crm_seq.nextval from dual")
	@Insert("insert into customer_activities(id,activity_date,description,place,title,customer_id) "
			+ "values(#{id},#{date},#{description},#{place},#{title},#{customer.id})")
	void save(CustomerActivity customerActivity);
	
	/**
	 * 根据交往记录 ID 查询一条交往记录
	 * @param id
	 * @return
	 */
	@Select("SELECT id,activity_date as \"date\",description,place,title,customer_id \"customer.id\" "
			+ "FROM customer_activities "
			+ "WHERE id = #{id}")
	CustomerActivity get(@Param("id") Integer id);
	
	/**
	 * 根据交往记录 ID 更新一条交往记录
	 * @param customerActivity
	 */
	@Update("UPDATE customer_activities set activity_date=#{date},description=#{description},place=#{place},title=#{title} "
			+ "WHERE id = #{id}")
	void update(CustomerActivity customerActivity);
	
	/**
	 * 根据交往记录 ID 删除一条交往记录
	 * @param id
	 */
	@Delete("DELETE FROM customer_activities WHERE id = #{id}")
	void delete(@Param("id") Integer id);
}
