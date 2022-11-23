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


	public void AddStudent(){
		//TODO:将账户设为student,参数未定,设置之前判断权限
		
	}

	public void AddAdmin(){
		//TODO:将账号设为管理员参数为定,设置之前判断权限
	}

	public void AddUserFromFIle(){
        //TODO:从文件读取记录批量导入数据库，使用预编译，参数为定
    }

	public User GetUserbyID(String id){
		return jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?",
            (ResultSet rs, int rowNum) -> {
                return new User( // new User object:
                        rs.getString("id"), // id
						rs.getString("name"),// name
                        rs.getString("password") // password
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

}
