package com.dao;

import java.sql.ResultSet;    
import java.sql.SQLException;    
import java.util.List;    
import org.springframework.jdbc.core.BeanPropertyRowMapper;    
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;    
import com.beans.Product;

public class ProductDao {
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}
	
	public void save(Product product) {
        String sql = "INSERT INTO product (productId, name, category, quantity, price, image_url) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql,product.getProductId(), product.getName(), product.getCategory(), product.getQuantity(), product.getPrice(), product.getImageUrl());
    }
	
	
}
