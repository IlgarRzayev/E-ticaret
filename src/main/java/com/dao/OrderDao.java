package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.beans.Order;
import com.beans.User;

public class OrderDao {	
	@Autowired
	JdbcTemplate template;    
	    
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}
	
	public int addOrder(Order order) {
		String sql = "insert into orders(productId, userId, count, paymentMethod) values('" + order.getProductId() + "','" + order.getUserId() + "','" + order.getCount() + "','" + order.getPaymentMethod() + "')";    
	    return template.update(sql);
	}
	public Order getOrdersByUserId(int userId) {
	    String sql = "select * from orders where userId=?";
	    try {
	        return template.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Order.class));
	    } catch (Exception e) {
	        return null;
	    }
	}
}	
	
