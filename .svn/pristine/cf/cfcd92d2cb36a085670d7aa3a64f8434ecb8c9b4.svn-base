package com.atguigu.crm.handlers;

import java.util.List;
import java.util.Map;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.services.AuthorityService;
import com.atguigu.crm.services.RoleService;
import com.atuigu.crm.entity.Authority;
import com.atuigu.crm.entity.Role;
import com.atuigu.crm.orm.Page;


@Controller
@RequestMapping("/role")
public class RoleHandler {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorityService authorityService;
	/**
	 * Role分页查询
	 * @author y_y
	 *
	 * @param pageNoStr
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,Map<String,Object> map){
		
		Page<Role> page = roleService.getRolesPage(pageNoStr);
		
		map.put("page", page);
		
		return "role/list";
	}
	
	/**
	 * 跳转至添加页面
	 * @author y_y
	 *
	 * @return
	 */
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input(){
		return "role/input";
	}
	
	/**
	 * 创建角色
	 * @author y_y
	 *
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String addRole(Role role){
		roleService.addRole(role);
		return "redirect:/role/list";
	}
	
	/**
	 * 删除角色
	 * @author y_y
	 *
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/delete/{roleId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("roleId") Integer roleId){
		
		roleService.deleteRoleById(roleId);
		
		return "redirect:/role/list";
	}
	
	/**
	 * 跳转至分配权限页面
	 * @author y_y
	 *
	 * @param roleId
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/assign/{roleId}",method=RequestMethod.GET)
	public String assign(@PathVariable("roleId") Integer roleId,Map<String,Object> map){
		
		Role role = roleService.getRoleByRoleId(roleId);
		List<Authority> list = authorityService.getAuthoritiesList();
		map.put("role", role);
		map.put("parentAuthorities",list);
		return "role/assign";
	}
	
	/**
	 * 分配权限
	 * @author y_y
	 *
	 * @param role
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/assign",method=RequestMethod.POST)
	public String updateAssign(Role role,Map<String,Object> map){
		
		roleService.updateAssgin(role);
		
		return "redirect:/role/list";
	}
	
	@ModelAttribute
	public void getRole(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		
		if (id!=null) {
			Role role = roleService.getRoleByRoleId(id);
			map.put("role", role);
		}
		
	}
}
