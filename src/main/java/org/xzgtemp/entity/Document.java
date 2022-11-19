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
	
	public Document(){

	}

	public Document(String id,String title,String author,String uploader,Date uploadtime,String filepath,Integer downloadtimes) {
		this.id=id;
		this.title=title;
		this.author=author;
		this.downloadtimes=downloadtimes;
		this.uploader=uploader;
		this.uploadtime=uploadtime;
		this.filepath=filepath;
	}

	//论文id
	public String GetID() {
		return id;
	}

	public void SetID(String id) {
		this.id=id;
	}
	
	//论文名
	public String GetTitle() {
		return title;
	}
	public void SetTitle(String title) {
		this.title=title;
	}
	
	//作者
	public String GetAuthor() {
		return author;
	}

	public void SetAuthor(String author) {
		this.author=author;
	}
	
	//上传信息
	public String GetUploader() {
		return uploader;
	}
	public void SetUploader(String uploader) {
		this.uploader=uploader;
	}
	public Date GetUploadtime() {
		return uploadtime;
	}
	public void SetUploadtime(Date uploadtime) {
		this.uploadtime=uploadtime;
	}
	
	//下载路径
	public String GetFilepath(){
		return filepath;
	}

	public void SetFilepath(String filepath) {
		this.filepath=filepath;
	}
    
	//下载次数
	public Integer GetDownloadtimes() {
		return downloadtimes;
	}

	public void SetDownloadtimes(Integer downloadtimes) {
		this.downloadtimes=downloadtimes;
	}
}
