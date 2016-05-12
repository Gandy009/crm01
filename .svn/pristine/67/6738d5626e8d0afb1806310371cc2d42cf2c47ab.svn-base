package com.atguigu.crm.test;

import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.services.SalesChanceService;
import com.atguigu.crm.services.SalesPlanService;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.entity.SalesPlan;
import com.atuigu.crm.entity.User;
import com.atuigu.crm.orm.Page;

public class ApplicationContextTest {

	private ApplicationContext ctx = null;
	private SalesChanceService salesChanceService;
	private SalesPlanService salesPlanService;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext*.xml");
		salesChanceService = ctx.getBean(SalesChanceService.class);
		salesPlanService = ctx.getBean(SalesPlanService.class);
	}
	
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("applicationContext*.xml");
	}
	
	@Test
	public void testSelectKey(){
		SalesPlan salesPlan = new SalesPlan();
		SalesChance chance = new SalesChance();
		chance.setId(1003L);
		salesPlan.setChance(chance);
		salesPlan.setDate(new Date());
		salesPlan.setTodo("AAAAAA");
		
		System.out.println(salesPlan.getId());
		salesPlanService.save(salesPlan);
		System.out.println(salesPlan.getId());
	}
	
	/**
	 * 测试无效的列类型.
	 * 1. 产生的原因: 在 mybatis 的 SQL 中实际传入的属性中有 null 的, 但 SQL 中使用了该属性. 则会出现 "无效的列类型" 的错误. 
	 * 2. 解决: 
	 * ①. 编写定制化的 SQL.
	 * ②. 添加 jdbcType 属性.  若使用此种方式, 则对应的属性值需要被设置为 null. 
	 */
	@Test
	public void testJdbcType(){
		SalesChance salesChance = new SalesChance();
		salesChance.setId(1177L);
		//salesChance.setDesigneeDate(new Date());
		User designee = new User();
		designee.setId(22L);
		salesChance.setDesignee(designee);
		
		//salesChanceService.update(salesChance);
		//salesChanceService.updateDesignee(salesChance);
		
		//session.update(chance);
		//@ModelAttribute
	}
	
	@Test
	public void testGetPage1(){
		Page<SalesChance> page = salesChanceService.getPage(4);
		
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPages());
		System.out.println(page.getContent());
	}
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
