package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;    
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;    
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;

import com.beans.Cart;
import com.beans.Product;
import com.beans.User;

public class ProductDao {
	@Autowired
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}
	
	public void save(Product product) {
        String sql = "INSERT INTO products (userId, name, category, price, count, image) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql,product.getUserId(), product.getName(), product.getCategory(), product.getPrice(),  product.getQuantity(), product.getImageUrl());
    }
	
	public int update(Product product){    
	    String sql="update products set name='" + product.getName() + "', category='" + product.getCategory() + "',price='" + product.getPrice() + "', count='" + product.getQuantity() + "',image='" + product.getImageUrl() + "' where productId="+product.getProductId()+"";    
	    return template.update(sql);    
	}    

	public int delete(int productId){    
	    String sql="delete from products where productId="+productId+"";    
	    return template.update(sql);    
	}

	/*
	 * public void deleteByUserId(int id) { String sql =
	 * "DELETE FROM products WHERE userId = ?"; template.update(sql, id); }
	 */
	public int deleteByUserId(int id){    
	    String sql="delete from products where userId="+id+"";    
	    return template.update(sql);    
	}
	
	public Product getProductById(int productId){    
	    String sql="select * from Products where productId=?";    
	    return template.queryForObject(sql, new Object[]{productId},new BeanPropertyRowMapper<Product>(Product.class));    
	}    

	/*
	 * public List<Product> getProductsByUserId(int userId) { String sql =
	 * "SELECT * FROM products WHERE userId=?"; return template.query(sql, new
	 * Object[] { userId }, new BeanPropertyRowMapper<Product>(Product.class)); }
	 */
	public List<Product> getProductsByUserId(int userId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE userId = ?";

        try (PreparedStatement statement = template.getDataSource().getConnection().prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setName(resultSet.getString("name"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("count"));
                product.setImageUrl(resultSet.getString("image"));
                products.add(product);
            }
        } catch (SQLException e) {
            // Handle SQLException as needed
            e.printStackTrace();
        }

        return products;
    }
	public List<Product> getProducts(){    
	    return template.query("select * from Products",new RowMapper<Product>(){    
	        public Product mapRow(ResultSet rs, int row) throws SQLException {    
	        	Product p=new Product();    
	            p.setProductId(rs.getInt("productId"));    
	            p.setName(rs.getString("name"));    
	            p.setCategory(rs.getString("category"));    
	            p.setPrice(rs.getDouble("price"));
	            p.setQuantity(rs.getInt("count"));
	            p.setImageUrl(rs.getString("image"));
	            return p;    
	        }    
	    });    
	}

	
}
