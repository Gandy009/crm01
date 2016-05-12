package com.atguigu.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.AuthorityMapper;
import com.atuigu.crm.entity.Authority;

@Service
@Transactional(readOnly=true)
public class AuthorityService {

	@Autowired
	private AuthorityMapper authorityMapper;
	
	/**
	 * 查询所有父权限
	 * @author y_y
	 *
	 * @return
	 */
	public List<Authority> getAuthoritiesList() {
		
		return authorityMapper.getParentAuthoritiesList();
	}

}
