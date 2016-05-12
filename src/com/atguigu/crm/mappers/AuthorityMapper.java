package com.atguigu.crm.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atuigu.crm.entity.Authority;

public interface AuthorityMapper {
	
	/**
	 * 查询所有父权限
	 * @author y_y
	 *
	 * @return
	 */
	List<Authority> getParentAuthoritiesList();
	
	/**
	 * 查询通过父权限ID查询所有子权限
	 * @author y_y
	 *
	 * @return
	 */
	List<Authority> getSubAuthoritiesList(@Param("parentId") Integer id);
	
	/**
	 * 查询通过子权限parent_authority_id查询父权限
	 * @author y_y
	 *
	 * @param id
	 * @return
	 */
	Authority getParentAuthorityBySubId(@Param("parentId") Integer id);
	
	/**
	 * 查询权限By角色ID
	 * @author y_y
	 *
	 * @param id
	 * @return
	 */
	
	List<Authority> getAuthoritiesByRoleId(@Param("roleId") Integer id);
	
}
