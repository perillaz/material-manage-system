package org.xzgtemp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer {
    
	@Autowired
	JdbcTemplate jdbcTemplate;
//TODO:更改初始化过程
//TODO：增加初始记录
//TODO：增加触发器
	@PostConstruct
	public void init() {
		//jdbcTemplate.update("DROP TABLE IF EXISTS User");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS User(id VARCHAR(13) PRIMARY KEY,name VARCHAR(30) NOT NULL,password VARCHAR(30) NOT NULL)");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS Book(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,title VARCHAR(100) NOT NULL,author VARCHAR(100) NOT NULL,buyer VARCHAR(13) NOT NULL,buytime Date NOT NULL,whereis VARCHAR(100) NOT NULL,isonshelf BOOLEAN NOT NULL,borrowtimes INTEGER NOT NULL,publishtime Date,publisher VARCHAR(100))");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS Document(id BIGINT PRIMARY KEY AUTO_INCREMENT,title VARCHAR(30) NOT NULL,author VARCHAR(30) NOT NULL,uploader VARCHAR(13) NOT NULL,uploadtime DATE NOT NULL,filepath VARCHAR(100) NOT NULL,downloadtimes INTEGER NOT NULL)");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS BorrowBook(id BIGINT PRIMARY KEY AUTO_INCREMENT,uid VARCHAR(20) NOT NULL,bid BIGINT NOT NULL,borrowtime DATE NOT NULL,sendbacktime DATE NOT NULL,duetime DATE NOT NULL,finished BOOLEAN NOT NULL)");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS DownloadDocument(id BIGINT PRIMARY KEY AUTO_INCREMENT,uid VARCHAR(13) NOT NULL,did BIGINT NOT NULL,downloadtime DATE NOT NULL)");
	}
}
