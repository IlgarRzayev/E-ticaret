package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.beans.Order;
import com.beans.User;

public class OrderDao {	
	@Autowired
	JdbcTemplate template;    
	    
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}
	
	public int addOrder(Order order) {
		String sql = "insert into orders(userId, productId, totalPrice, paymentMethod) values('" + order.getUserId() + "','" + order.getProductId() + "','" + order.getTotalPrice() + "','" + order.getPaymentMethod() + "')";    
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
	public int delete(int orderId){    
	    String sql="delete from orders where orderId="+orderId+"";    
	    return template.update(sql);    
	}
	
	public Order getOrderById(int id){    
	    String sql="select * from orders where orderId=?";    
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Order>(Order.class));    
	}
	public List<Order> getOrders(){    
	    return template.query("select * from Orders",new RowMapper<Order>(){    
	        public Order mapRow(ResultSet rs, int row) throws SQLException {    
	        	Order o=new Order();    
	            o.setOrderId(rs.getInt(1));  
	            o.setProductId(rs.getInt(2));
	            o.setUserId(rs.getInt(3));    
	            o.setTotalPrice(rs.getDouble(4));
	            o.setPaymentMethod(rs.getString(5));
	            return o;    
	        }    
	    });
}	
}

