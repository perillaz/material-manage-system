package org.xzgtemp.service;

import java.sql.PreparedStatement;

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
		return jdbcTemplate.queryForObject("SELECT * FROM User WHERE u_id = ?", new Object[] { id },
				userRowMapper);
	}

	public User register(String id, String name, String password) {
		User user = new User();
		user.SetID(id);
		user.SetName(name);
		user.SetPassword(password);
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

    public void ChangeUserPassword(User user) {
		if (1 != jdbcTemplate.update("UPDATE User SET u_password = ? WHERE u_id = ? ", user.GetPassword(), user.GetID())) {
			throw new RuntimeException("User not found by id");
		}
	}


    public void ChangeUserName(User user) {
		if (1 != jdbcTemplate.update("UPDATE User SET u_name = ? WHERE u_id = ? ", user.GetName(), user.GetID())) {
			throw new RuntimeException("User not found by id");
		}
	}

}
