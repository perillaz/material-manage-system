package org.xzgtemp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xzgtemp.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=AppConfig.class)
public class TestInit {

	@Autowired
	JdbcTemplate jdbcTemplate;

    @Test
    public void testinit(){
        for(int i = 1;i <= 40; i ++){
        jdbcTemplate.update(
            "INSERT IGNORE INTO User(id,name,password,usertype,setAdmini,setStudent) VALUES (?,?,'0000','student',false,false) ",
            String.valueOf(i),
            "user" + String.valueOf(i)
            );
        }
    }
}
