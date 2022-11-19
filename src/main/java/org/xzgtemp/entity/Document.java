package org.xzgtemp.entity;

import java.sql.Date;

public class Document {
	private String id;
	private String title;
	private String author;
	private String uploader;
	private Date uploadtime;
	private String filepath;
	private Integer downloadtimes;
	
	public Document(String id,String title,String author,Integer downloadtimes,String uploader,Date uploadtime,String filepath) {
		this.id=id;
		this.title=title;
		this.author=author;
		this.downloadtimes=downloadtimes;
		this.uploader=uploader;
		this.uploadtime=uploadtime;
		this.filepath=filepath;
	}
	
	//论文id
	public String GetDoc() {
		return id;
	}
	public void SetDoc(String id) {
		this.id=id;
	}
	
	//论文名
	public String Gettitle() {
		return title;
	}
	public void Modifytitle(String title) {
		this.title=title;
	}
	
	//作者
	public String Getauthor() {
		return author;
	}
	public void Modifyauthor(String author) {
		this.author=author;
	}
	
	//上传信息
	public String Getuploader() {
		return uploader;
	}
	public void Setuploader(String uploader) {
		this.uploader=uploader;
	}
	public Date Getuploadtime() {
		return uploadtime;
	}
	public void Setuploadtime(Date uploadtime) {
		this.uploadtime=uploadtime;
	}
	
	//下载路径
	public String Getpath() {
		return filepath;
	}
	public void Setpath(String filepath) {
		this.filepath=filepath;
	}
    
	//下载次数
	public Integer Gettimes() {
		return downloadtimes;
	}
	public void Settimes(Integer downloadtimes) {
		this.downloadtimes=downloadtimes;
	}
}
