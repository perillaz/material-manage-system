package org.xzgtemp.entity;

import java.sql.Date;

public class Book {

	private Long id;
	private String title;
	private String author;
	private Integer copyamount;
	private Integer borrowedcopys = 0;
	private Integer allborrowtimes=0;  //所有copys的借阅次数
	private String isbn;
	private String edition;
	private Date publishtime;
	private String publisher;
	private String lang;
	private String briefinfo;
	
	public Book(){
		
	}


	public Book(Long id, String title, String author, Integer copyamount, Integer borrowedcopys, Integer allborrowtimes,
			String isbn, String edition, Date publishtime, String publisher, String lang, String briefinfo) {
		setId(id);
		setTitle(title);
		this.author = author;
		this.copyamount = copyamount;
		this.borrowedcopys = borrowedcopys;
		this.allborrowtimes = allborrowtimes;
		this.isbn = isbn;
		this.edition = edition;
		this.publishtime = publishtime;
		this.publisher = publisher;
		this.lang = lang;
		this.briefinfo = briefinfo;
	}

//batch
	public Book(String title, String author, Integer copyamount, Integer borrowedcopys, String isbn, String edition,
			Date publishtime, String publisher, String lang, String briefinfo) {
		setTitle(title);
		this.author = author;
		this.copyamount = copyamount;
		this.borrowedcopys = borrowedcopys;
		this.isbn = isbn;
		this.edition = edition;
		this.publishtime = publishtime;
		this.publisher = publisher;
		this.lang = lang;
		this.briefinfo = briefinfo;
	}


	public Book(
		Long id,
		String title,
		String author,
		Integer copyamount,
		Integer borrowedcopys
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCopyamount(copyamount);
		setBorrowedcopys(borrowedcopys);
	}

	public Book(
			Long id,
			String title,
			String author,
			Integer copyamount,
			Integer borrowedcopys,
			String briefinfo
		) {
			setId(id);
			setTitle(title);
			setAuthor(author);
			setCopyamount(copyamount);
			setBriefinfo(briefinfo);
			setBorrowedcopys(borrowedcopys);
		}

	public Book(
			Long id,
			String title,
			String author,
			Integer copyamount,
			Integer borrowedcopys,
			Date publishtime,
			String publisher
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCopyamount(copyamount);
		setBorrowedcopys(borrowedcopys);
		setPublisher(publisher);
		setPublishtime(publishtime);
		
	}
	
	public Book(
			Long id,
			String title,
			String author,
			Integer copyamount,
			Integer borrowedcopys,
			Date publishtime,
			String publisher,
			String briefinfo
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCopyamount(copyamount);
		setBorrowedcopys(borrowedcopys);
		setPublisher(publisher);
		setPublishtime(publishtime);
		setBriefinfo(briefinfo);
		
	}
	
	public Book(
			Long id,
			String title,
			String author,
			Integer copyamount,
			Integer borrowedcopys,
			Integer allborrowtimes,
			Date publishtime,
			String publisher,
			String briefinfo
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCopyamount(copyamount);
		setBorrowedcopys(borrowedcopys);
		setPublisher(publisher);
		setPublishtime(publishtime);
		setBriefinfo(briefinfo);
		
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

	public Integer getCopyamount() {
		return copyamount;
	}
	public void setCopyamount(Integer copyamount) {
		this.copyamount=copyamount;
	}


	public Integer getBorrowedcopys() {
		return borrowedcopys;
	}
	public void setBorrowedcopys(Integer borrowedcopys) {
		this.borrowedcopys=borrowedcopys;
	}


	public Integer getAllborrowtimes() {
		return allborrowtimes;
	}
	public void setAllborrowtimes(Integer allborrowtimes) {
		this.allborrowtimes=allborrowtimes;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
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


	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public String getBriefinfo() {
		return briefinfo;
	}
	public void setBriefinfo(String briefinfo) {
		this.briefinfo=briefinfo;
	}

}

