package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atuigu.crm.entity.Role;

public interface RoleMapper {

	@Select("SELECT id,name FROM roles")
	Role getById(Integer id);
	
	/**
	 * 查询角色的数量
	 * @author y_y
	 *
	 * @return
	 */
	long getTotalRoles();
	
	/**
	 * Role分页查询
	 * @author y_y
	 *
	 * @param params
	 * @return
	 */
	List<Role> getRoleList(Map<String, Object> params);
	
	/**
	 * 删除角色
	 * @author y_y
	 *
	 * @param roleId
	 */
	void deleteRoleById(@Param("roleId") Integer roleId);
	
	/**
	 * 通过Id查询Role及其权限
	 * @author y_y
	 *
	 * @return
	 */
	Role getRoleById(@Param("roleId") Integer roleId);
	
	/**
	 * 删除roleId的所有权限
	 * @author y_y
	 *
	 * @param roleId
	 */
	void deleteAssgin(@Param("roleId") long roleId);

	/**
	 * 给角色分配权限
	 * @author y_y
	 *
	 * @param role
	 */
	void updateAssgin(Role role);
	
	/**
	 * 创建角色
	 * @author y_y
	 *
	 * @param role
	 */
	void insertRole(Role role);
	
	/**
	 * 查询所用的角色用户集合（只含有 ID 和 name）
	 * @return
	 */
	@Select("SELECT id,name FROM roles")
	List<Role> getRoels();
}
