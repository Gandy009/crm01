package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.ProductMapper;
import com.atuigu.crm.entity.Product;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

/**
 * 产品信息的逻辑业务处理
 * @author xiaoqing
 *
 */
@Service
@Transactional(readOnly=true)
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * 分页信息显示的逻辑业务
	 * @param pageNo
	 * @param params
	 * @return
	 */
	public Page<Product> getPage(int pageNo, Map<String, Object> params) {
		Page<Product> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 把请求参数转为 mybatis 可以使用的请求参数
		Map<String , Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		//2. 查询 totalElements
		int totalElements = (int) productMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		//4. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		
		//5. 调方法查询 content
		List<Product> content = productMapper.getContent(mybatisParams);
		page.setContent(content);
		
		return page;
	}
	
	/**
	 * 根据产品 ID 获取 Product的逻辑业务
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Product get(int id) {
		
		return productMapper.getProductById(id);
	}
	
	/**
	 * 保存数据的逻辑业务
	 * @param product
	 */
	public void save(Product product) {
		
		productMapper.save(product);
	}
	
	/**
	 * 更新数据的逻辑业务
	 * @param product
	 */
	public void update(Product product) {
		
		productMapper.update(product);
	}
	
	/**
	 * 根据 ID 删除数据的逻辑业务
	 * @param id
	 */
	public void delete(Integer id) {
		
		productMapper.delete(id);
	}

}
