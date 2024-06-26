package com.dao;

import com.beans.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

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

	public void save(Product product) {
        String sql = "INSERT INTO products (userId, name, category, price, count) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,product.getUserId(), product.getName(), product.getCategory(), product.getPrice(),  product.getQuantity());
    }
	
	public int update(Product product){    
	    String sql="update products set name='" + product.getName() + "', category='" + product.getCategory() + "',price='" + product.getPrice() + "', count='" + product.getQuantity() + "',image='" + product.getImageUrl() + "' where productId="+product.getProductId()+"";    
	    return jdbcTemplate.update(sql);    
	}    

	public int delete(int productId){    
	    String sql="delete from products where productId="+productId+"";    
	    return jdbcTemplate.update(sql);    
	}

	/*
	 * public void deleteByUserId(int id) { String sql =
	 * "DELETE FROM products WHERE userId = ?"; template.update(sql, id); }
	 */
	public int deleteByUserId(int id){    
	    String sql="delete from products where userId="+id+"";    
	    return jdbcTemplate.update(sql);    
	}

	public Product getProductById(int productId){    
	    String sql="select * from Products where productId=?";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{productId},new BeanPropertyRowMapper<Product>(Product.class));    
	}    

	public Product getProductByCartId(int cartId) {
	    String sql="SELECT p.* FROM carts as c, products as p where c.cartId="+cartId;    
	    return jdbcTemplate.queryForObject(sql,new RowMapper<Product>(){    
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
	/*
	 * public List<Product> getProductsByUserId(int userId) { String sql =
	 * "SELECT * FROM products WHERE userId=?"; return template.query(sql, new
	 * Object[] { userId }, new BeanPropertyRowMapper<Product>(Product.class)); }
	 */
	public List<Product> getProductsByUserId(int userId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE userId = ?";

        try (PreparedStatement statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(query)) {
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
	    return jdbcTemplate.query("select * from Products",new RowMapper<Product>(){    
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
