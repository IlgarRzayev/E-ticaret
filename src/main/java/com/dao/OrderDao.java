package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.beans.Order;

public class OrderDao {	
	@Autowired
	JdbcTemplate template;    
	    
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}
	
	public int save(Order order) {
		String sql = "insert into orders(productId, userId, count, paymentMethod) values('" + order.getProductId() + "','" + order.getUserId() + "','" + order.getCount() + "','" + order.getPaymentMethod() + "')";    
	    return template.update(sql);
	}
}	
