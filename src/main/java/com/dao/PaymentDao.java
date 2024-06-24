package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.beans.Cart;
import com.beans.Payment;

public class PaymentDao {
	@Autowired
	JdbcTemplate template;    
	    
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}
	
	
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO payment (userId, totalPrice) VALUES (?, ?)";
        template.update(sql, payment.getUserId(), payment.getTotalPrice());
    } 
    public int delete(int cartId){    
        String sql="delete from payment where cartId="+cartId+"";    
        return template.update(sql);    
    }
    public List<Payment> getPaymentsByUserId(int userId) {
        String sql = "SELECT * FROM payment WHERE userId=?";
        return template.query(sql, new Object[] { userId }, new BeanPropertyRowMapper<Payment>(Payment.class));
    }
    
}
