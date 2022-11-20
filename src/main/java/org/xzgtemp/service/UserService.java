package org.xzgtemp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import org.xzgtemp.entity.User;

@Component
public class UserService {
    
	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);

	public User GetUserbyID(String id){
		return jdbcTemplate.queryForObject("SELECT * FROM User WHERE u_id = ?",
            (ResultSet rs, int rowNum) -> {
                return new User( // new User object:
                        rs.getString("u_id"), // id
						rs.getString("u_name"),// name
                        rs.getString("u_password") // password
				); 
            },
			id
		);
	}

	public User register(String id, String name, String password) {
		User user = new User(id,name,password);
		if (1 != jdbcTemplate.update((conn) -> {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO User(u_id,u_name,u_password) VALUES(?,?,?)");
			ps.setObject(1, user.GetID());
			ps.setObject(3, user.GetName());
			ps.setObject(2, user.GetPassword());
			return ps;
		})) {
			throw new RuntimeException("Insert failed.");
		}
		return user;
	}

    public User signin(String id, String password){
		User user = GetUserbyID(id);
		if (user.GetPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("login failed.");
    }

    public void ChangeUserPassword(User user,String password) {
		if (1 != jdbcTemplate.update("UPDATE User SET u_password = ? WHERE u_id = ? ", password, user.GetID())) {
			throw new RuntimeException("User not found by id");
		}
	}


    public void ChangeUserName(User user,String name) {
		if (1 != jdbcTemplate.update("UPDATE User SET u_name = ? WHERE u_id = ? ", name, user.GetID())) {
			throw new RuntimeException("User not found by id");
		}
	}

}
