package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.beans.Cart;

public class CartDao {
	@Autowired
	JdbcTemplate template;    
	    
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}
	
	
    public void addCartItem(Cart cartItem) {
        String sql = "INSERT INTO carts (userId, productId, quantity) VALUES (?, ?, ?)";
        template.update(sql, cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity());
    } 
    
    
    public List<Cart> getCartItemsByUserId(int userId) {
        String sql = "SELECT * FROM cart WHERE user_id=?";
        return template.query(sql, new Object[] { userId }, new BeanPropertyRowMapper<Cart>(Cart.class));
    }

    
    public void removeCartItem(int cartId) {
        String sql = "DELETE FROM carts WHERE cartId=?";
        template.update(sql, cartId);
    }

    
    public void clearCartByUserId(int userId) {
        String sql = "DELETE FROM carts WHERE userId=?";
        template.update(sql, userId);
    }
}
