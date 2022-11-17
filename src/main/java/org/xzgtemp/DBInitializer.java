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
		//jdbcTemplate.update("CREATE TABLE TEST( ID int,name VARCHAR(20),PRIMARY KEY (ID))");
	}
}
