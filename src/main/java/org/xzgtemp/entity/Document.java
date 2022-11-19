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
	
	//获取论文id
	public String GetDoc() {
		return id;
	}
	
	//论文名
	public String Gettitle() {
		return title;
	}
	
	//作者
	public String Getauthor() {
		return author;
	}
	
	//修改
	public void Modifytitle(String title) {
		this.title=title;
	}
	
	public void Modifyauthor(String author) {
		this.author=author;
	}
	
	//获取上传信息
	public String Getuploader() {
		return uploader;
	}
	public Date Getuploadtime() {
		return uploadtime;
	}
	
	//下载路径
	public String Getpath() {
		return filepath;
	}
    
	//下载次数
	public Integer Gettimes() {
		return downloadtimes;
	}
}
