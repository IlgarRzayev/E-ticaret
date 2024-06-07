package com.dao;

import java.sql.ResultSet;    
import java.sql.SQLException;    
import java.util.List;    
import org.springframework.jdbc.core.BeanPropertyRowMapper;    
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;    
import com.beans.User;    
    
public class UserDao {    
JdbcTemplate template;    
    
public void setTemplate(JdbcTemplate template) {    
    this.template = template;    
}    
public int save(User p) {    
    String sql = "insert into User(name, surname, email, password) values('" + p.getName() + "','" + p.getSurname() + "','" + p.getEmail() + "','" + p.getPassword() + "')";    
    return template.update(sql);    
}

public int update(User p){    
    String sql="update User set name='" + p.getName() + "','" + p.getSurname() + "','" + p.getEmail() + "','" + p.getPassword() + "' where userId="+p.getId()+"";    
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
public List<User> getEmployees(){    
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
