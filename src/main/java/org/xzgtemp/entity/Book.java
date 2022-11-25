package org.xzgtemp.entity;

import java.sql.Date;

public class Book {

	private Long id;
	private String title;
	private String author;
	private Integer copyamount;
	private Integer borrowcopys;
	private Integer allborrowtimes=0;  //所有copys的借阅次数
	private Date publishtime;
	private String publisher;
	private String brief_info;
	
	//TODO:关于author的拆分
	//TODO: 增加其他的类似publishtime这样的可空的属性如isbn等等，参照校图书馆。。。
	//TODO：增加copyamount相关函数
	//TODO:改变构造函数,设置buytime,borrowtime等为初始值
	public Book(){
		
	}

	public Book(
		Long id,
		String title,
		String author,
		Integer copyamount,
		Integer borrowcopys
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCopyamount(copyamount);
		this.borrowcopys=0;
	}

	public Book(
			Long id,
			String title,
			String author,
			Integer copyamount,
			Integer borrowcopys,
			String brief_info
		) {
			setId(id);
			setTitle(title);
			setAuthor(author);
			setCopyamount(copyamount);
			setBrief_info(brief_info);
			this.borrowcopys=0;
			//this.borrowtimes=0;
		}

	public Book(
			Long id,
			String title,
			String author,
			Integer copyamount,
			Integer borrowcopys,
			Date publishtime,
			String publisher
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCopyamount(copyamount);
		this.borrowcopys=0;
		setPublisher(publisher);
		setPublishtime(publishtime);
		
		
	}
	
	public Book(
			Long id,
			String title,
			String author,
			Integer copyamount,
			Integer borrowcopys,
			Date publishtime,
			String publisher,
			String brief_info
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCopyamount(copyamount);
		this.borrowcopys=0;
		setPublisher(publisher);
		setPublishtime(publishtime);
		setBrief_info(brief_info);
		
	}
	
	public Book(
			Long id,
			String title,
			String author,
			Integer copyamount,
			Integer borrowcopys,
			Integer allborrowtimes,
			Date publishtime,
			String publisher,
			String brief_info
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCopyamount(copyamount);
		this.borrowcopys=0;
		setPublisher(publisher);
		setPublishtime(publishtime);
		setBrief_info(brief_info);
		
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
	public String getBrief_info() {
		return brief_info;
	}
	public void setBrief_info(String brief_info) {
		this.brief_info=brief_info;
	}
	public Integer getBorrowcopys() {
		return borrowcopys;
	}
	public void setBorrowcopys(Integer borrowcopys) {
		this.borrowcopys=borrowcopys;
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
	public Integer getAllborrowtimes() {
		return allborrowtimes;
	}
	public void setAllborrowtimes(Integer allborrowtimes) {
		this.allborrowtimes=allborrowtimes;
	}
	
	
	

	
    
}

