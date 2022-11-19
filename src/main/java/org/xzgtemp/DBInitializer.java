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
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS Book( b_id VARCHAR(20) PRIMARY KEY AUTO_INCREMENT,b_title VARCHAR(100),b_author VARCHAR(100),b_publishtime Date,b_publisher VARCHAR(100),b_buyer VARCHAR(13),b_buytime Date,b_whereis VARCHER(100) ,b_isonshelf BOOLEAN,b_borrowtimes INTEGER,b_ordered BOOLEAN, b_orderuser VARCHAR(20))");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS Document( d_id VARCHAR(20) PRIMARY KEY AUTO_INCREMENT,d_title VARCHAR(30),u_author VARCHAR(30),d_uploaduser VARCHAR(13),d_uploadtime DATE,d_filepath VARCHAR(100),d_downloadtimes INTEGER)");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS BorrowBook( bb_id VARCHAR(20) PRIMARY KEY AUTO_INCREMENT,bb_uid VARCHAR(20),bb_bid VARCHAR(20),bb_borrowtime DATE,bb_sendbacktime DATE, bb_ddlofborrow DATE, bb_hadback BOOLEAN, bb_ordered BOOLEAN, bb_orderuser VARCHAR(20))");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS DownloadDocument( dd_id VARCHAR(20) PRIMARY KEY AUTO_INCREMENT,dd_uid VARCHAR(20),dd_did VARCHAR(20), dd_downloadtime DATE)");
	}
}
