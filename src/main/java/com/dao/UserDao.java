package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import com.beans.User;

public class UserDao {
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

	public int save(User p) {
		String sql = "insert into User(name, surname, email, password) values('" + p.getName() + "','" + p.getSurname()
				+ "','" + p.getEmail() + "','" + p.getPassword() + "')";
		return jdbcTemplate.update(sql);
	}

	public User getUserByEmailAndPassword(String email, String password) {
		String sql = String.format("select * from user where email=\"%s\" and password=\"%s\"", email, password);
		try {
			return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int row) throws SQLException {
					User e = new User();
					e.setId(rs.getInt(1));
					e.setName(rs.getString(2));
					e.setSurname(rs.getString(3));
					e.setEmail(rs.getString(4));
					e.setPassword(rs.getString(5));
					return e;
				}
			});

		} catch (Exception e) {
			return null;
		}
	}

	public User getUserByEmail(String email) {
		String sql = "select * from User where email=" + email;
		try {
			return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int row) throws SQLException {
					User e = new User();
					e.setId(rs.getInt(1));
					e.setName(rs.getString(2));
					e.setSurname(rs.getString(3));
					e.setEmail(rs.getString(4));
					e.setPassword(rs.getString(5));
					return e;
				}
			});

		} catch (Exception e) {
			return null;
		}
	}

	public int update(User p) {
		String sql = "update User set name='" + p.getName() + "', surname='" + p.getSurname() + "', email='"
				+ p.getEmail() + "',password='" + p.getPassword() + "' where userId=" + p.getId() + "";
		return jdbcTemplate.update(sql);
	}

	public int delete(int id) {
		String sql = "delete from User where userId=" + id;
		return jdbcTemplate.update(sql);
	}

	public User getUserById(int id) {
		String sql = "select * from User where userId=" + id;
		try {
			return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int row) throws SQLException {
					User e = new User();
					e.setId(rs.getInt(1));
					e.setName(rs.getString(2));
					e.setSurname(rs.getString(3));
					e.setEmail(rs.getString(4));
					e.setPassword(rs.getString(5));
					return e;
				}
			});

		} catch (Exception e) {
			return null;
		}
	}

	public List<User> getUsers() {
		return jdbcTemplate.query("select * from User", new RowMapper<User>() {
			public User mapRow(ResultSet rs, int row) throws SQLException {
				User e = new User();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSurname(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setPassword(rs.getString(5));
				return e;
			}
		});
	}
}
