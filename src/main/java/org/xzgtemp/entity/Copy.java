package org.xzgtemp.entity;

import java.sql.Date;

public class Copy {
    private Long id;
    private Long bid;
    private String loc;
    private Boolean isborrowed;
    private Boolean isreserved;
    private String buyer;
    private Date buytime;
    private Integer borrowtimes;

    public Copy() {
    	
    }
    
    public Copy(
    		Long id,
    		Long bid,
    		String loc,
    		Boolean isborrowed,
    		Boolean isreserved,
    		Integer borrowtimes
    		
    		) {
    	setId(id);
    	bid=getBid();
    	setLoc(loc);
    	setIsborrowed(isborrowed);
    	setIsreserved(isreserved);
    	this.borrowtimes=0;
    }
    
    public Copy(
    		Long id,
    		Long bid,
    		String loc,
    		Boolean isborrowed,
    		Boolean isreserved,
    		Integer borrowtimes,
    		String buyer,
    		Date buytime
    		
    		) {
    	setId(id);
    	bid=getBid();
    	setLoc(loc);
    	setIsborrowed(isborrowed);
    	setIsreserved(isreserved);
    	this.borrowtimes=0;
    	setBuyer(buyer);
    	setBuytime(buytime);
    }
    
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	if(id <= 0){
			throw new IllegalArgumentException("invalid id value");
		}
    	this.id=id;
    }
    public Long getBid() {
    	return bid;
    }
    
    public String getLoc() {
    	return loc;
    }
    public void setLoc(String loc) {
    	this.loc=loc;
    }
    public Boolean getIsborrowed() {
    	return isborrowed;
    }
    public void setIsborrowed(Boolean isborrowed) {
    	this.isborrowed=isborrowed;
    }
    public Boolean getIsreserved() {
    	return isreserved;
    }
    public void setIsreserved(Boolean isreserved) {
    	this.isreserved=isreserved;
    }
    public Integer getBorrowtimes() {
    	return borrowtimes;
    }
    public void setBorrowtimes(Integer borrowtimes) {
    	this.borrowtimes=borrowtimes;
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
}


