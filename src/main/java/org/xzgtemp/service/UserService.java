package org.xzgtemp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import org.xzgtemp.entity.User;

@Component
public class UserService {
    
	@Autowired
	JdbcTemplate jdbcTemplate;

    public User register(String name, String email, String passwrd){
        return null;
    }


    public Boolean signin(String email, String passwrd){
        return null;
    }

}
