package org.xzgtemp.entity;

public class WeekBorrowRank {
    private Long bookid;
    private String booktitle;
    private Integer borrowtimes;

    public WeekBorrowRank() {
    }
    public Long getBookid() {
        return bookid;
    }
    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }
    public String getBooktitle() {
        return booktitle;
    }
    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }
    public Integer getBorrowtimes() {
        return borrowtimes;
    }
    public void setBorrowtimes(Integer borrowtimes) {
        this.borrowtimes = borrowtimes;
    }
    @Override
    public String toString() {
        return "WeekBorrowRank [bookid=" + bookid + ", booktitle=" + booktitle + ", borrowtimes=" + borrowtimes + "]";
    }
}
