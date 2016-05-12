package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.atuigu.crm.entity.Product;


public interface ProductMapper {
 
	Product getProductById(@Param("id")Integer id);
	
	/**
	 * 查询产品总记录数
	 * @param mybatisParams
	 * @return
	 */
	int getTotalElements(Map<String, Object> mybatisParams);
	
	/**
	 * 查询产品数据的集合
	 * @param mybatisParams
	 * @return
	 */
	@Select("SELECT id,batch,memo,name,price,type,unit FROM products")
	List<Product> getContent(Map<String, Object> mybatisParams);
	
	/**
	 * 插入一条产品数据
	 * @param product
	 */
	@SelectKey(before=true,keyColumn="id",keyProperty="id",resultType=Long.class,statement="select crm_seq.nextval from dual")
	@Insert("INSERT INTO products(id,batch,memo,name,price,type,unit) VALUES(#{id},#{batch},#{memo},#{name},#{price},#{type},#{unit})")
	void save(Product product);
	
	/**
	 * 更新一条数据
	 * @param product
	 */
	void update(Product product);
	
	/**
	 * 根据 ID 删除一条数据
	 * @param id
	 */
	@Delete("delete from products where id = #{id}")
	void delete(@Param("id") Integer id);
}
