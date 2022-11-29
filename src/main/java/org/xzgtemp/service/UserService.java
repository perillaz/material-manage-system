package org.xzgtemp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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


	public void AddStudent(User user, List<User> users){
		if(user.getSetstudent()){
			for(User User : users){
				User.setUsertype("student");
				ChangeUserpermission(User);
			}
		}
	}

	public void AddAdmini(User user,List<User> users){
		if(user.getSetadmini()){
			for(User User : users){
				User.setUsertype("admini");
				ChangeUserpermission(User);
			}
		}
	}

	public void AddAdmini(User user,List<User> users,Boolean setAdmini,Boolean setStudent){
		if(user.getSetadmini()){
			for(User User : users){
				User.setUsertype("admini");
				User.setSetadmini(setAdmini);
				User.setSetstudent(setStudent);
				ChangeUserpermission(User);
			}
		}
	}


	public User GetUserbyID(String id){
		return jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?",
            (ResultSet rs, int rowNum) -> {
                return new User( // new User object:
                        rs.getString("id"), // id
						rs.getString("name"),// name
                        rs.getString("password") ,// password
						rs.getString("usertype"),
						rs.getBoolean("setadmini"),
						rs.getBoolean("setstudent")
				); 
            },
			id
		);
	}

	public User register(String id, String name, String password) {
		User user = new User(id,name,password);
		if (1 != jdbcTemplate.update((conn) -> {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO User(id,name,password) VALUES(?,?,?)");
			ps.setObject(1, user.getId());
			ps.setObject(3, user.getName());
			ps.setObject(2, user.getPassword());
			return ps;
		})) {
			throw new RuntimeException("Insert failed.");
		}
		return user;
	}

    public User signin(String id, String password){
		User user = GetUserbyID(id);
		if (user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("login failed.");
    }

    public void ChangeUserPassword(User user) {
		if (1 != jdbcTemplate.update("UPDATE User SET password = ? WHERE id = ? ",
			user.getPassword(),
			user.getId()
		)) {
			throw new RuntimeException("User not found by id");
		}
	}


    public void ChangeUserName(User user) {
		if (1 != jdbcTemplate.update("UPDATE User SET name = ? WHERE id = ? ", user.getName(), user.getId())) {
			throw new RuntimeException("User not found by id");
		}
	}

	public void ChangeUserpermission(User user) {
		if (1 != jdbcTemplate.update("UPDATE User SET usertype = ? ,setAdmini = ?,setStudent = ?  WHERE id = ? ",
				user.getUsertype(),
				user.getSetadmini(),
				user.getSetstudent(),
				user.getId()
		)) {
			throw new RuntimeException("User not found by id");
		}
	}

}
