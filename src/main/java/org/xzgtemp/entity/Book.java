package org.xzgtemp.entity;

import java.sql.Date;

public class Book {
	private String id;
	private String title;
	private String author;
	private Date publishtime;
	private String publisher;
	private String buyer;
	private Date buytime;
	private String whereis;
	private Boolean isonshelf;
	private Integer borrowtimes;
	private Boolean ordered;
	private String orderuser;
	
	
	public Book(String id,String title,String author,Date publishtime,String publisher,String buyer,Date buytime,String whereis,Boolean isonshelf,Integer borrowtimes,Boolean ordered,String orderuser) {
		this.id=id;
		this.title=title;
		this.author=author;
		this.publishtime=publishtime;
		this.publisher=publisher;
		this.buyer=buyer;
		this.buytime=buytime;
		this.whereis=whereis;
		this.isonshelf=isonshelf;
		this.borrowtimes=borrowtimes;
		this.ordered=ordered;
		this.orderuser=orderuser;
		
	}
	
	//获取书籍信息
	public String GetBook() {
		return id;
	}
	public String Gettitle() {
		return title;
	}
	public String Getauthor() {
		return author;
	}
	public String Getpublisher() {
		return publisher;
	}
	public Date Getpublishtime() {
		return publishtime;
	}
	public String Getbuyer() {
		return buyer;
	}
	public Date Getbuytime() {
		return buytime;
	}
	//获取书籍位置
	public String Whereis() {
		return whereis;
	}
	//书籍是否在架上
	public Boolean Onshelf() {
		return isonshelf;
	}
	//被借次数
	public Integer Borrowtimes() {
			return borrowtimes;
	}
	//预定
	public Boolean Ordered() {
		return ordered;
	}
	public String Orderuser() {
		return orderuser;
	}
	
	//修改
	public void SetBook(String id) {
		this.id=id;
	}
	public void Modifytitle(String title) {
		this.title=title;
	}
	public void Modifyauthor(String author) {
		this.author=author;
	}
	public void SetPublishtime(Date publishtime) {
		this.publishtime=publishtime;
	}
	public void SetPubilsher(String publisher) {
		this.publisher=publisher;
	}
	public void Setbuyer(String buyer) {
		this.buyer=buyer;
	}
	public void Setbuytime(Date buytime) {
		this.buytime=buytime;
	}
	public void Setlocation(String whereis) {
		this.whereis=whereis;
	}
	public void SetOnshelf(Boolean isonshelf) {
		this.isonshelf=isonshelf;
	}
	public void SetBorrowtimes(Integer borrowtimes) {
		this.borrowtimes=borrowtimes;
	}
	public void SetOrder(Boolean ordered) {
		this.ordered=ordered;
	}
	public void SetOrderuser(String orderuser) {
		this.orderuser=orderuser;
	}
	
	
		
	
	
	
	
	
	
	
	

    
}

