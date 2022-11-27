package org.xzgtemp.entity;

import java.sql.Date;

public class Copy {
    private Long id;
    private Long bid;
	private String btitle;
    private String loc;
    private Boolean isborrowed;
    private Boolean isreserved;
    private String buyerid;
    private String buyername;
    private Date buytime;
    private Integer borrowtimes = 0;
    private Boolean canbeborrow = true;
    private Boolean canbereserve = true;
    private String reserver = "";

    public String getReserver() {
        return reserver;
    }

    public void setReserver(String reserver) {
        this.reserver = reserver;
    }

    public Copy() {
    	
    }

    public Copy(Long id, Long bid, String btitle, String loc, Boolean isborrowed, Boolean isreserved, String buyerid,
            String buyername, Date buytime, Integer borrowtimes, Boolean canbeborrow, Boolean canbereserve,
            String reserver) {
        this.id = id;
        this.bid = bid;
        this.btitle = btitle;
        this.loc = loc;
        this.isborrowed = isborrowed;
        this.isreserved = isreserved;
        this.buyerid = buyerid;
        this.buyername = buyername;
        this.buytime = buytime;
        this.borrowtimes = borrowtimes;
        this.canbeborrow = canbeborrow;
        this.canbereserve = canbereserve;
        this.reserver = reserver;
    }

    public Copy(Long bid, String btitle, String loc, Boolean isborrowed, Boolean isreserved, String buyerid,
            String buyername, Date buytime) {
        this.bid = bid;
        this.btitle = btitle;
        this.loc = loc;
        this.isborrowed = isborrowed;
        this.isreserved = isreserved;
        this.buyerid = buyerid;
        this.buyername = buyername;
        this.buytime = buytime;
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
    public void setBid(Long bid) {
    	
    	this.bid=bid;
    }
    
	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
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
    public String getBuyerid() {
    	return buyerid;
    }
    public void setBuyerid(String buyerid) {
    	this.buyerid=buyerid;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    public Date getBuytime() {
    	return buytime;
    }
    public void setBuytime(Date buytime) {
    	this.buytime=buytime;
    }

    public Boolean getCanbeborrow() {
        return canbeborrow;
    }

    public void setCanbeborrow(Boolean canbeborrow) {
        this.canbeborrow = canbeborrow;
    }

    public Boolean getCanbereserve() {
        return canbereserve;
    }

    public void setCanbereserve(Boolean canbereserve) {
        this.canbereserve = canbereserve;
    }


}


