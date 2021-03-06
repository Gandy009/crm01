package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.RoleMapper;
import com.atuigu.crm.entity.Role;
import com.atuigu.crm.orm.Page;

@Service
@Transactional(readOnly=true)
public class RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 角色分页查询
	 * @author y_y
	 *
	 * @param pageNo
	 * @return
	 */
	public Page<Role> getRolesPage(String pageNoStr){
		long totalRoles = (long) roleMapper.getTotalRoles(); 
		
		int pageNo = 1;
		
		try{
			pageNo = Integer.parseInt(pageNoStr);
		}catch(Exception e){}
		
		Page<Role> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(2);
		page.setTotalElements((int)totalRoles);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String,Object> params = new HashMap<>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<Role> roles = roleMapper.getRoleList(params);
		
		page.setContent(roles);
		
		return page;
	}
	
	/**
	 * 删除角色
	 * @author y_y
	 *
	 * @param roleId
	 */
	@Transactional(readOnly=false)
	public void deleteRoleById(Integer roleId) {
		roleMapper.deleteRoleById(roleId);
	}

	/**
	 * 通过ID查询Role及其权限
	 * @author y_y
	 *
	 * @param roleId
	 * @return
	 */
	public Role getRoleByRoleId(Integer roleId) {
		return roleMapper.getRoleById(roleId);
	}
	
	/**
	 * 给角色分配权限
	 * @author y_y
	 *
	 * @param role
	 */
	@Transactional(readOnly=false)
	public void updateAssgin(Role role) {
		roleMapper.deleteAssgin(role.getId());
		roleMapper.updateAssgin(role);
	}
	
	/**
	 * 创建角色
	 * @author y_y
	 *
	 * @param role
	 */
	public void addRole(Role role) {
		roleMapper.insertRole(role);
	}
	
	/**
	 * 获取所有角色的逻辑业务
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Role> getRoles() {

		return roleMapper.getRoels();
	}
}
