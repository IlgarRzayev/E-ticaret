package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
        String sql = "INSERT INTO payment (userId, productId, cartId, quantity) VALUES (?, ?, ?)";
        template.update(sql, payment.getUserId(), payment.getProductId(),payment.getCartId(), payment.getQuantity());
    } 
    
}
