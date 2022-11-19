package org.xzgtemp.entity;

import java.sql.Date;

public class BorrowBook {
	private String borrowid;
	private String book_id;
	private String user_id;
	private Date borrowdate;
	private Date sendbackdate;
	private Date ddlofborrow;
	private Boolean hadback;
	private Boolean ordered;
	private String orderuser;
	
	
	public BorrowBook(String borrowid, String book_id, String user_id,Date borrowdate, Date sendbackdate,Date ddlofborrow,Boolean hadback,Boolean ordered,String orderuser) {
		this.borrowid=borrowid;
		this.book_id=book_id;
		this.user_id=user_id;
		this.borrowdate=borrowdate;
		this.sendbackdate=sendbackdate;
		this.ddlofborrow=ddlofborrow;
		this.hadback=hadback;
		this.ordered=ordered;
		this.orderuser=orderuser;
	}
	
	//借书
	public String Getborrowid() {
		return borrowid;
	}
	public String Getbook_id() {
		return book_id;
	}
	public String Getuser_id() {
		return user_id;
	}
	//借出时间
	public Date Getborrdate() {
		return borrowdate;
	}
	//归还时间
	public Date Getbacktime() {
		return sendbackdate;
	}
	//应还时间
	public Date Theddl() {
		return ddlofborrow;
	}
	
	
	//是否归还
	public Boolean Backornot() {
		return hadback;
	}
	
	//是否被预定
	public Boolean Ordered() {
		return ordered;
	}
	public String Orderuser() {
		return orderuser;
	}
	
	//修改借书信息
	public void Setbook_id(String book_id) {
		
	}
	public void Setuser_id(String user_id) {
		
	}
	
	
	
    
}

