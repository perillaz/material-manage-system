package org.xzgtemp.entity;

import java.sql.Date;

public class BorrowBook {
	private Long id;
	private String uid;
	private Long bid;
	private Date borrowtime;
	private Date sendbacktime;
	private Date duetime;
	private Boolean finished;
	
	public BorrowBook(){

	}

	public BorrowBook(
		Long id,
		String uid,
		Long bid,
		Date borrowtime,
		Date sendbacktime,
		Date duetime,
		Boolean finished){
			this.id = id;
			this.uid = uid;
			this.bid = bid;
			this.borrowtime = borrowtime;
			this.sendbacktime = sendbacktime;
			this.duetime = duetime;
			this.finished = finished;
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

	public Long GetBID() {
		return bid;
	}

	public void SetBID(Long bid){
		this.bid = bid;
	}

	public Date GetBorrowtime() {
		return borrowtime;
	}

	public void SetBorrowtime(Date borrowtime){
		this.borrowtime = borrowtime;
	}

	public Date GetSendbacktime() {
		return sendbacktime;
	}

	public void SetSendbacktime(Date sendbacktime){
		this.sendbacktime = sendbacktime;
	}

	public Date GetDuetime() {
		return duetime;
	}

	public void SetDuetime(Date duetime){
		this.duetime = duetime;
	}


	public Boolean GetFinished() {
		return finished;
	}

	public void SetFinished(Boolean finished){
		this.finished = finished;
	}
	
    
}

