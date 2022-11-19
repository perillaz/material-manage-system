package org.xzgtemp.entity;

import java.sql.Date;

public class Book {

	private Long id;
	private String title;
	private String author;
	private String buyer;
	private Date buytime;
	private String whereis;
	private Boolean isonshelf;
	private Integer borrowtimes;
	private Date publishtime;
	private String publisher;
	
	public Book(Long id,String title,String author,String buyer,Date buytime,String whereis,Boolean isonshelf,Integer borrowtimes) {
		this.id=id;
		this.title=title;
		this.author=author;
		this.buyer=buyer;
		this.buytime=buytime;
		this.whereis=whereis;
		this.isonshelf=isonshelf;
		this.borrowtimes=borrowtimes;
	}
	
	public Book(Long id,String title,String author,Date publishtime,String publisher,String buyer,Date buytime,String whereis,Boolean isonshelf,Integer borrowtimes,Boolean ordered,String orderuser) {
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
	}
	
	public Long  GetID(){
		return id;
	}

	public void SetID(Long id){
		this.id = id;
	}

	public String GetTitle() {
		return title;
	}

	public void SetTitle(String title) {
		this.title=title;
	}

	public String GetAuthor() {
		return author;
	}
	public void SetAuthor(String author) {
		this.author=author;
	}
	public String Getbuyer() {
		return buyer;
	}

	public void Setbuyer(String buyer) {
		this.buyer=buyer;
	}

	public Date Getbuytime() {
		return buytime;
	}

	public void Setbuytime(Date buytime) {
		this.buytime=buytime;
	}

	public String GetWhereis() {
		return whereis;
	}

	public void SetWhereis(String whereis ){
		this.whereis = whereis;
	}

	public Boolean GetIsonshelf() {
		return isonshelf;
	}

	public void SetIsonshelf(Boolean isonshelf) {
		this.isonshelf=isonshelf;
	}

	public Integer GetBorrowtimes() {
		return borrowtimes;
	}

	public void SetBorrowtimes(Integer borrowtimes) {
		this.borrowtimes=borrowtimes;
	}

	public Date GetPublishtime() {
		return publishtime;
	}

	public void SetPublishtime(Date publishtime) {
		this.publishtime=publishtime;
	}

	public String GetPublisher() {
		return publisher;
	}

	public void SetPubilsher(String publisher) {
		this.publisher=publisher;
	}
    
}

