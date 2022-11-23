package org.xzgtemp.entity;

import java.sql.Date;

public class BorrowBook {
	private Long id;
	private String uid;
	private Long cid;
	private Date borrowtime;
	private Date sendbacktime;
	private Date duetime;
	private Boolean finished;
	
	public BorrowBook(){

	}

	public BorrowBook(
		Long id,
		String uid,
		Long cid,
		Date borrowtime,
		Date sendbacktime,
		Date duetime,
		Boolean finished){
			this.id = id;
			this.uid = uid;
			this.cid = cid;
			this.borrowtime = borrowtime;
			this.sendbacktime = sendbacktime;
			this.duetime = duetime;
			this.finished = finished;
		}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid){
		this.uid = uid;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid){
		this.cid = cid;
	}

	public Date getBorrowtime() {
		return borrowtime;
	}

	public void setBorrowtime(Date borrowtime){
		this.borrowtime = borrowtime;
	}

	public Date getSendbacktime() {
		return sendbacktime;
	}

	public void setSendbacktime(Date sendbacktime){
		this.sendbacktime = sendbacktime;
	}

	public Date getDuetime() {
		return duetime;
	}

	public void setDuetime(Date duetime){
		this.duetime = duetime;
	}


	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished){
		this.finished = finished;
	}
	
    
}

