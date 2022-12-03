package org.xzgtemp.entity;

public class WeekDownloadRank {
    private Long documentid;
    private String documenttitle;
    private Integer downloadtimes;
    
    public WeekDownloadRank() {
    }

    public Long getDocumentid() {
        return documentid;
    }
    public void setDocumentid(Long documentid) {
        this.documentid = documentid;
    }
    public String getDocumenttitle() {
        return documenttitle;
    }
    public void setDocumenttitle(String documenttitle) {
        this.documenttitle = documenttitle;
    }
    public Integer getDownloadtimes() {
        return downloadtimes;
    }
    public void setDownloadtimes(Integer downloadtimes) {
        this.downloadtimes = downloadtimes;
    }

    @Override
    public String toString() {
        return "WeekDownloadRank [documentid=" + documentid + ", documenttitle=" + documenttitle + ", downloadtimes="
                + downloadtimes + "]";
    }
    
}
