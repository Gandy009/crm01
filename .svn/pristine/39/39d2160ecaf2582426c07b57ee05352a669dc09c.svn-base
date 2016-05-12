package com.atguigu.crm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mappers.DictMapper;
import com.atuigu.crm.entity.Dict;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

@Service
public class DictService {
	
	@Autowired
	private DictMapper dictMapper;

	public List<String> getRegions() {
		List<String> list = new ArrayList<>();
		List<Dict> regions = dictMapper.getAllRegion();
		for (Dict dict : regions) {
			list.add(dict.getItem());
		}
		return list;
	}

	public List<String> getLevels() {
		List<String> list = new ArrayList<>();
		List<Dict> levels = dictMapper.getAllLevel();
		for (Dict dict : levels) {
			list.add(dict.getItem());
		}
		return list;
	}

	public List<String> getSatisfies() {
		List<String> list = new ArrayList<>();
		List<Dict> satisfies = dictMapper.getAllSatisfie();
		for (Dict dict : satisfies) {
			list.add(dict.getItem());
		}
		return list;
	}

	public List<String> getCredits() {
		List<String> list = new ArrayList<>();
		List<Dict> credits = dictMapper.getAllCredit();
		for (Dict dict : credits) {
			list.add(dict.getItem());
		}
		return list;
	}
	/**
	 * page
	 * @param pageNo
	 * @return
	 * @author Mars Lou
	 * @param params2 
	 * @date 2016年4月1日
	 */
	public Page<Dict> getPages(int pageNo, Map<String, Object> params) {
		
		Page<Dict> page=new Page<>();
		page.setPageNo(pageNo);
		
		//1. 把请求参数转为 mybatis 可以使用的请求参数
		Map<String , Object> mybatisParams =PropertyFilter.parseRequestParams2MybatisParams(params);
			
		//2. 查询 totalElements
		int totalElements=(int) dictMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		//4. 把 firstIndex 和 endIndex 加入到 mybatisParams 中	
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		//5. 调方法查询 content
		List<Dict> content=dictMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	/**
	 * save
	 * @param dict
	 * @author Mars Lou
	 * @date 2016年4月1日
	 */
	public void save(Dict dict) {
		dictMapper.save(dict);
	}

	/**
	 * delete
	 * @param id
	 * @author Mars Lou
	 * @date 2016年4月1日
	 */
	public void delete(Integer id) {
		
		dictMapper.delete(id);
	}

	/**
	 * getDictById
	 * @param id
	 * @author Mars Lou
	 * @return 
	 * @date 2016年4月1日
	 */
	public Dict getDictById(Integer id) {
		
		return dictMapper.getDictById(id);
		
	}

	public void update(Dict dict) {

		dictMapper.update(dict);
		
	}

}
