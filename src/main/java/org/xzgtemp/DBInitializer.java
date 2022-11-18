package org.xzgtemp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer {
    
	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		//jdbcTemplate.update("DROP TABLE IF EXISTS User");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS User( u_id VARCHAR(13),u_name VARCHAR(30),u_password VARCHAR(30),PRIMARY KEY (u_id))");
	}
}
