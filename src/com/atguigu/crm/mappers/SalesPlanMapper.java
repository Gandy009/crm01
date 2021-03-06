package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {

	List<SalesChance> getContent2(Map<String, Object> params);
	
	long getTotalElements2(Map<String, Object> params);

	SalesChance getSalesChanceById(@Param("id") Integer id);
	
	List<SalesPlan> getPlanById(@Param("id") Integer id);

	void save(SalesPlan salesPlan);

	SalesPlan getSalesPlanById(Integer id);

	Integer updatePlan(SalesPlan salesPlan);

	int deletePlan(Integer id);
}
