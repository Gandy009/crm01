package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.UserMapper;
import com.atuigu.crm.entity.User;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User login(String name, String password){
		User user = userMapper.getByName(name);
		
		//直接比较密码. 但实际开发中没有这么比较的!
		if(user != null 
				&& user.getEnabled() == 1
				&& user.getPassword().equals(password)){
			return user;
		}
		
		return null;
	}
	
	public List<User> getUserList(){
		List<User> users = userMapper.getUserList();
		return users;
	}
	
	/**
	 * 系统用户分页的逻辑业务
	 * @param pageNo
	 * @param params
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<User> getPage(int pageNo, Map<String, Object> params) {
		Page<User> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 把请求参数转为 mybatis 可以使用的请求参数
		Map<String , Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		//2. 查询 totalElements
		int totalElements = (int) userMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		//4. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		
		//5. 调方法查询 content
		List<User> content = userMapper.getContent(mybatisParams);
		page.setContent(content);
		
		return page;
	}
	
	/**
	 * 保存新用户的逻辑业务
	 * @param attributes
	 */
	public void save(User user) {
		
		userMapper.save(user);
	}
	
	/**
	 * 根据 ID 获取user的逻辑业务
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public User get(Integer id) {
		
		return userMapper.get(id);
	}
	
	/**
	 * 更新用户的逻辑业务
	 * @param user
	 */
	public void update(User user) {

		userMapper.update(user);
	}
	
	/**
	 * 根据 ID 删除用户的逻辑业务
	 * @param id
	 */
	public void delete(Integer id) {
		
		userMapper.delete(id);
	}
}
