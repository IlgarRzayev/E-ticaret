package com.dao;

import java.sql.ResultSet;    
import java.sql.SQLException;    
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;    
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;    
import com.beans.User;    
    
public class UserDao {    
@Autowired
JdbcTemplate template;    
    
public void setTemplate(JdbcTemplate template) {    
    this.template = template;    
}    
public int save(User p) {    
    String sql = "insert into User(name, surname, email, password) values('" + p.getName() + "','" + p.getSurname() + "','" + p.getEmail() + "','" + p.getPassword() + "')";    
    return template.update(sql);    
}

public User getUserByEmailAndPassword(String email, String password) {
    String sql = "select * from User where email=? and password=?";
    try {
        return template.queryForObject(sql, new Object[]{email, password}, new BeanPropertyRowMapper<>(User.class));
    } catch (Exception e) {
        return null;
    }
}
public User getUserByEmail(String email) {
    String sql = "select * from User where email=?";
    try {
        return template.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
    } catch (Exception e) {
        return null;
    }
}


public int update(User p){    
    String sql="update User set name='" + p.getName() + "', surname='" + p.getSurname() + "', email='" + p.getEmail() + "',password='" + p.getPassword() + "' where userId="+p.getId()+"";    
    return template.update(sql);    
}    

public int delete(int id){    
    String sql="delete from User where userId="+id+"";    
    return template.update(sql);    
}    
public User getUserById(int id){    
    String sql="select * from User where userId=?";    
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<User>(User.class));    
}    
public List<User> getUsers(){    
    return template.query("select * from User",new RowMapper<User>(){    
        public User mapRow(ResultSet rs, int row) throws SQLException {    
        	User e=new User();    
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
