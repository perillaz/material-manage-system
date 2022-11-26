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
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS User(id VARCHAR(13) PRIMARY KEY,name VARCHAR(30) NOT NULL,password VARCHAR(30) NOT NULL,usertype VARCHAR (10) NOT NULL DEFAULT 'visitor',setadmini BOOLEAN NOT NULL DEFAULT 0,setstudent BOOLEAN NOT NULL DEFAULT 0)");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS Book(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,title VARCHAR(50) NOT NULL,author VARCHAR(50) NOT NULL,copyamount INTEGER NOT NULL DEFAULT 0,borrowedcopys INTEGER NOT NULL DEFAULT 0,allborrowtimes INTEGER NOT NULL DEFAULT 0,isbn VARCHAR(13),edition VARCHAR(30),publishtime Date,publisher VARCHAR(30),lang VARCHAR(10),briefinfo VARCHAR(1000))");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS Copy(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,bid BIGINT NOT NULL,btitle VARCHAR(50) NOT NULL,loc VARCHAR(50) NOT NULL,isborrowed BOOLEAN NOT NULL DEFAULT 0,isreserved BOOLEAN NOT NULL DEFAULT 0,buyerid VARCHAR(13) NOT NULL,buyername VARCHAR(30) NOT NULL,buytime Date NOT NULL,borrowtimes INTEGER NOT NULL DEFAULT 0)");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS Document(id BIGINT PRIMARY KEY AUTO_INCREMENT,title VARCHAR(30) NOT NULL,author VARCHAR(30) NOT NULL,uploaderid VARCHAR(13) NOT NULL,uploadername VARCHAR(30) NOT NULL,uploadtime DATE NOT NULL,filepath VARCHAR(100) NOT NULL,downloadtimes INTEGER NOT NULL,doi VARCHAR(20),literature VARCHAR(30))");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS BorrowCopy(id BIGINT PRIMARY KEY AUTO_INCREMENT,uid VARCHAR(20) NOT NULL,cid BIGINT NOT NULL,bid BIGINT NOT NULL,btitle VARCHAR(50) NOT NULL,borrowtime DATE NOT NULL,sendbacktime DATE NOT NULL,duetime DATE NOT NULL,finished BOOLEAN NOT NULL)");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS DownloadDocument(id BIGINT PRIMARY KEY AUTO_INCREMENT,uid VARCHAR(13) NOT NULL,did BIGINT NOT NULL,dtitle VARCHAR(50) NOT NULL,downloadtime DATE NOT NULL)");
		jdbcTemplate.update("DROP TRIGGER IF EXISTS BookTitleSynchronization;");
		jdbcTemplate.update("DROP TRIGGER IF EXISTS DocumentTitleSynchronization;");
		jdbcTemplate.update("DROP TRIGGER IF EXISTS UserNameSynchronization;");
		jdbcTemplate.update("DROP TRIGGER IF EXISTS BorrowCopyCount;");
		jdbcTemplate.update("DROP TRIGGER IF EXISTS DownloadDocumentCount;");
		jdbcTemplate.update("CREATE TRIGGER BookTitleSynchronization AFTER UPDATE ON Book FOR EACH ROW IF NEW.title<>OLD.title THEN BEGIN UPDATE Copy SET btitle = NEW.title WHERE bid = NEW.id;UPDATE BorrowCopy SET btitle = NEW.title WHERE bid = NEW.id;END ;END IF");
		jdbcTemplate.update("CREATE TRIGGER DocumentTitleSynchronization AFTER UPDATE ON Document FOR EACH ROW IF NEW.title<>OLD.title THEN UPDATE DownloadDocument SET documenttitle = NEW.title WHERE did = NEW.id;END IF");
		jdbcTemplate.update("CREATE TRIGGER UserNameSynchronization AFTER UPDATE ON User FOR EACH ROW IF NEW.name<>OLD.name THEN BEGIN UPDATE Book SET buyername = NEW.name WHERE buyerid = NEW.id;UPDATE Document SET uploadername = NEW.name WHERE uploaderid = NEW.id;END ;END IF");
		jdbcTemplate.update("CREATE TRIGGER BorrowCopyCount AFTER INSERT ON BorrowCopy FOR EACH ROW BEGIN UPDATE Book SET allborrowtimes = allborrowtimes + 1 WHERE id = NEW.bid; UPDATE Copy SET borrowtimes = borrowtimes + 1 WHERE id = NEW.cid; END");
		jdbcTemplate.update("CREATE TRIGGER DownloadDocumentCount AFTER INSERT ON DownloadDocument FOR EACH ROW UPDATE Document SET downloadtimes = downloadtimes + 1 WHERE id = NEW.did");
		jdbcTemplate.update("INSERT IGNORE INTO User(id,name,password,usertype,setAdmini,setStudent) VALUES ('0000','Administrator','0000','admini',true,true) ");
	}
}
