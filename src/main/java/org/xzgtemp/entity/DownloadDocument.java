package org.xzgtemp.entity;

import java.sql.Date;

public class DownloadDocument {
	private String download_id;
	private String d_id;
	private String user_id;
	private Date downloadtime;
	
	public DownloadDocument(String download_id,String d_id, String user_id,Date downloadtime) {
		this.download_id=download_id;
		this.d_id=d_id;
		this.user_id=user_id;
		this.downloadtime=downloadtime;
	}
	
	//下载信息
	public String Getdownload_id() {
		return download_id;
	}
	public String Getdou() {
		return d_id;
	}
	public String Getuser() {
		return user_id;
	}
	public Date Downloadtime() {
		return downloadtime;
	}
	
	//修改下载信息
	public void Setdou(String d_id) {
		this.d_id=d_id;
	}
	public void Setuser(String user_id) {
		this.user_id=user_id;
	}
	
	
    
}
