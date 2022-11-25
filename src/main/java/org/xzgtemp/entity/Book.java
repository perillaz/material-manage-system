package org.xzgtemp.entity;

import java.sql.Date;

public class Book {

	private Long id;
	private String title;
	private String author;
	private String buyer;
	private Date buytime;
	private Integer copyamount;
	//private String whereis;
	//private Boolean isonshelf;
	private Integer borrowtimes = 0;
	private Date publishtime;
	private String publisher;
	
	//!!!更改后的book没有idonshelf和whereis属性，这两个是单册（copy）的属性
	//TODO：去掉所有isonshelf和whereis相关的语句
	//TODO:关于author的拆分
	//TODO: 增加其他的类似publishtime这样的可空的属性如isbn等等，参照校图书馆。。。
	//TODO：增加copyamount相关函数
	//TODO:改变构造函数,设置buytime,borrowtime等为初始值
	public Book(){
		
	}

	public Book(
		String title,
		String author,
		String buyer,
		Date buytime,
	) {
		setTitle(title);
		setAuthor(author);
		setBuyer(buyer);
		setBuytime(buytime);
		setWhereis(whereis);
		setIsonshelf(isonshelf);
		this.borrowtimes=0;
	}

	public Book(
		String title,
		String author,
		String buyer,
		Date buytime,
		String whereis,
		Boolean isonshelf,
		Date publishtime,
		String publisher
	) {
		setTitle(title);
		setAuthor(author);
		setBuyer(buyer);
		setBuytime(buytime);
		setWhereis(whereis);
		setIsonshelf(isonshelf);
		setPublishtime(publishtime);
		setPublisher(publisher);
	}

	public Book(
		Long id,
		String title,
		String author,
		String buyer,
		Date buytime,
		String whereis,
		Boolean isonshelf,
		Integer borrowtimes
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setBuyer(buyer);
		setBuytime(buytime);
		setWhereis(whereis);
		setIsonshelf(isonshelf);
		setBorrowtimes(borrowtimes);
	}
	
	public Book(
		String title,
		String author,
		String buyer,
		Date buytime,
		String whereis,
		Boolean isonshelf,
		Integer borrowtimes,
		Date publishtime,
		String publisher
	) {
		setTitle(title);
		setAuthor(author);
		setBuyer(buyer);
		setBuytime(buytime);
		setWhereis(whereis);
		setIsonshelf(isonshelf);
		setBorrowtimes(borrowtimes);
		setPublishtime(publishtime);
		setPublisher(publisher);
	}
	public Book(
		Long id,
		String title,
		String author,
		String buyer,
		Date buytime,
		String whereis,
		Boolean isonshelf,
		Integer borrowtimes,
		Date publishtime,
		String publisher
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setBuyer(buyer);
		setBuytime(buytime);
		setWhereis(whereis);
		setIsonshelf(isonshelf);
		setBorrowtimes(borrowtimes);
		setPublishtime(publishtime);
		setPublisher(publisher);
	}
	

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		if(id <= 0){
			throw new IllegalArgumentException("invalid id value");
		}
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title == ""){
			throw new IllegalArgumentException("invalid title value");
		}
		this.title=title;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer=buyer;
	}

	public Date getBuytime() {
		return buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime=buytime;
	}

	public String getWhereis() {
		return whereis;
	}

	public void setWhereis(String whereis ){
		this.whereis = whereis;
	}

	public Boolean getIsonshelf() {
		return isonshelf;
	}

	public void setIsonshelf(Boolean isonshelf) {
		this.isonshelf=isonshelf;
	}

	public Integer getBorrowtimes() {
		return borrowtimes;
	}

	public void setBorrowtimes(Integer borrowtimes) {
		this.borrowtimes=borrowtimes;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime=publishtime;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher=publisher;
	}
    
}

