package org.xzgtemp.entity;

import java.sql.Date;

public class Document {

	private Long id;
	private String title;
	private String author;
	private String uploaderid;
	private String uploadername;
	private Date uploadtime;
	private String filepath;
	private Integer downloadtimes = 0;
	private String doi;
	private String literature;//期刊或会议名

	public Document(){
	}

	public Document(Long id, String title, String author, String uploaderid, String uploadername, Date uploadtime,
			String filepath, Integer downloadtimes, String doi, String literature) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.uploaderid = uploaderid;
		this.uploadername = uploadername;
		this.uploadtime = uploadtime;
		this.filepath = filepath;
		this.downloadtimes = downloadtimes;
		this.doi = doi;
		this.literature = literature;
	}



	public Document(String title, String author, String uploaderid, String uploadername, Date uploadtime,
			String filepath, String doi, String literature) {
		this.title = title;
		this.author = author;
		this.uploaderid = uploaderid;
		this.uploadername = uploadername;
		this.uploadtime = uploadtime;
		this.filepath = filepath;
		this.doi = doi;
		this.literature = literature;
	}

	//论文id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}
	
	//论文名
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title == ""){
			throw new IllegalArgumentException("invalid title value");
		}
		this.title=title;
	}
	
	//作者
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author=author;
	}
	

	
	public String getUploaderid() {
		return uploaderid;
	}


	public void setUploaderid(String uploaderid) {
		this.uploaderid = uploaderid;
	}


	public String getUploadername() {
		return uploadername;
	}


	public void setUploadername(String uploadername) {
		this.uploadername = uploadername;
	}


	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime=uploadtime;
	}
	
	//下载路径
	public String getFilepath(){
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath=filepath;
	}
    
	//下载次数
	public Integer getDownloadtimes() {
		return downloadtimes;
	}

	public void setDownloadtimes(Integer downloadtimes) {
		this.downloadtimes=downloadtimes;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getLiterature() {
		return literature;
	}

	public void setLiterature(String literature) {
		this.literature = literature;
	}

	
}
