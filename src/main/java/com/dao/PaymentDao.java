package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.beans.Cart;
import com.beans.Payment;

public class PaymentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public JdbcTemplate setJdbcTemplate(JdbcTemplate template) {
		return this.jdbcTemplate = template;
	}

	 public int savePayment(Payment payment) {
	        String sql = "INSERT INTO payments (userId, totalPrice) VALUES (?, ?)";
	        return jdbcTemplate.update(sql, payment.getUserId(), payment.getTotalPrice());
	    } 
	 
    public int delete(int cartId){    
        String sql="delete from payment where cartId="+cartId+"";    
        return jdbcTemplate.update(sql);    
    }
    public Payment getPaymentByUserId(int userId) {
        String sql = "SELECT * FROM payments WHERE userId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Payment.class));
    }
    
}
