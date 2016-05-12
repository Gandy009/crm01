package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.atuigu.crm.entity.User;

public interface UserMapper {

	//查询出 User 关联的 Role 的 name 属性
	//使用的是 JavaBean 的级联属性. User 有一个 Role 类型的 role 属性, Role 中还有一个 name 属性.
	//于是 role.name 就是 User 的级联属性. 
	@Select("SELECT u.id, u.name, password, u.enabled, r.name as \"role.name\" "
			+ "FROM users u "
			+ "LEFT OUTER JOIN roles r "
			+ "ON role_id = r.id "
			+ "WHERE u.name = #{name}")
	User getByName(@Param("name") String name);
	
	List<User> getUserList();
	
	/**
	 * 根据权限获取用户
	 * @author y_y
	 *
	 * @param id
	 * @return
	 */
	Set<User> getUserByRoleId(@Param("roleId") Integer id);
	
	/**
	 * 查询系统用户的总记录数
	 * @param mybatisParams
	 * @return
	 */
	int getTotalElements(Map<String, Object> mybatisParams);
	
	/**
	 * 查询系统用户的集合
	 * @return
	 */
	List<User> getContent(Map<String, Object> mybatisParams);
	
	/**
	 * 插入一个新用户
	 * @param user
	 */
	@SelectKey(before=true,keyColumn="id",keyProperty="id",statement="select crm_seq.nextval from dual",resultType=Long.class)
	@Insert("INSERT INTO users(id,enabled,name,password,role_id) "
			+ "values(#{id},#{enabled},#{name},#{password},#{role.id})")
	void save(User user);
	
	/**
	 * 根据 ID 查询用户
	 * @param id
	 * @return
	 */
	@Select("select id,enabled,name,password,role_id as \"role.id\" FROM users where id=#{id}")
	User get(@Param("id") Integer id);
	
	/**
	 * 更新一条用户信息
	 * @param user
	 */
	void update(User user);
	
	/**
	 * 根据 ID 删除一条用户信息
	 * @param id
	 */
	@Delete("DELETE FROM users WHERE id = #{id}")
	void delete(@Param("id") Integer id);
	

	
}
