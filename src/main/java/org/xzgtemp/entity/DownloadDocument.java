package org.xzgtemp.entity;

import java.sql.Date;

public class DownloadDocument {
	private Long id;
	private String uid;
	private Long did;
	private Date downloadtime;
	
	public DownloadDocument(Long id,String uid,Long did,Date downloadtime) {
		this.id=id;
		this.did=did;
		this.uid=uid;
		this.downloadtime=downloadtime;
	}
	
	public Long GetID() {
		return id;
	}

	public void SetID(Long id){
		this.id = id;
	}

	public String GetUID() {
		return uid;
	}

	public void SetUID(String uid){
		this.uid = uid;
	}

	public Long GetDID() {
		return did;
	}

	public void SetDID(Long did){
		this.did = did;
	}

	public Date GetDownloadtime(){
		return downloadtime;
	}

	public void SetDownloadtime(Date downloadtime){
		this.downloadtime = downloadtime;
	}
    
}
