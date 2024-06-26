package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.beans.Cart;
import com.beans.User;

public class CartDao {
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


	public void addCartItem(Cart cartItem) {
		String sql = "INSERT INTO carts (userId, productId, quantity, price) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity(),
				cartItem.getPrice());
	}

	public int delete(int cartId) {
		String sql = "delete from carts where cartId=" + cartId + "";
		return jdbcTemplate.update(sql);
	}

	public List<Cart> getCartItemsByUserId(int userId) {
		String sql = "SELECT * FROM carts WHERE userId=?";
		return jdbcTemplate.query(sql, new Object[] { userId }, new BeanPropertyRowMapper<Cart>(Cart.class));
	}

	public void deleteByUserId(int id) {
		String sql = "DELETE FROM carts WHERE userId = ?";
		jdbcTemplate.update(sql, id);
	}

	public void deleteByProductId(int userId, int productId) {
		String sql = "DELETE FROM carts WHERE userId = ? AND productId = ?";
		jdbcTemplate.update(sql, userId, productId);
	}

	public List<Cart> getCarts() {
		return jdbcTemplate.query("select * from Carts", new RowMapper<Cart>() {
			public Cart mapRow(ResultSet rs, int row) throws SQLException {
				Cart c = new Cart();
				c.setCartId(rs.getInt(1));
				c.setUserId(rs.getInt(2));
				c.setProductId(rs.getInt(3));
				c.setQuantity(rs.getInt(4));
				c.setPrice(rs.getDouble(5));
				return c;
			}
		});
	}
}
