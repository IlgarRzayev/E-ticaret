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
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public JdbcTemplate setJdbcTemplate(JdbcTemplate template) {
		return this.jdbcTemplate = template;
	}

	public int addOrder(Order order) {
		String sql = "insert into orders(userId, productId, totalPrice, paymentMethod) values('" + order.getUserId()
				+ "','" + order.getProductId() + "','" + order.getTotalPrice() + "','" + order.getPaymentMethod()
				+ "')";
		return jdbcTemplate.update(sql);
	}

	public List<Order> getOrdersByUserId(int userId) {
		String sql = "select * from orders where userId="+userId;
		try {
			return jdbcTemplate.query(sql, new RowMapper<Order>() {
				@Override
				public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
					Order o = new Order();
					o.setOrderId(rs.getInt(1));
					o.setProductId(rs.getInt(2));
					o.setUserId(rs.getInt(3));
					o.setTotalPrice(rs.getDouble(4));
					o.setPaymentMethod(rs.getString(5));
					return o;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int delete(int orderId) {
		String sql = "delete from orders where orderId=" + orderId;
		return jdbcTemplate.update(sql);
	}

	public Order getOrderById(int id) {
		String sql = "select * from orders where orderId="+id;
		return jdbcTemplate.queryForObject(sql, new RowMapper<Order>() {
			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order o = new Order();
				o.setOrderId(rs.getInt(1));
				o.setProductId(rs.getInt(2));
				o.setUserId(rs.getInt(3));
				o.setTotalPrice(rs.getDouble(4));
				o.setPaymentMethod(rs.getString(5));
				return o;
			}
		});
	}

	public int updateOrder(Order order) {
        String sql = "UPDATE orders SET totalPrice = ?, paymentMethod = ? WHERE orderId = ?";
        return jdbcTemplate.update(sql, order.getTotalPrice(), order.getPaymentMethod(), order.getOrderId());
    }

	
	public List<Order> getOrders() {
		return jdbcTemplate.query("select * from Orders", new RowMapper<Order>() {
			public Order mapRow(ResultSet rs, int row) throws SQLException {
				Order o = new Order();
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
