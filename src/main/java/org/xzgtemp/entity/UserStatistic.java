package org.xzgtemp.entity;

public class UserStatistic {
    private String userid;
    private String username;
    private Integer uploadtimes;
    private Integer addcopytimes;

    public UserStatistic() {
    }

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getUploadtimes() {
        return uploadtimes;
    }
    public void setUploadtimes(Integer uploadtimes) {
        this.uploadtimes = uploadtimes;
    }
    public Integer getAddcopytimes() {
        return addcopytimes;
    }
    public void setAddcopytimes(Integer addcopytimes) {
        this.addcopytimes = addcopytimes;
    }

    @Override
    public String toString() {
        return "UserStatistic [userid=" + userid + ", username=" + username + ", uploadtimes=" + uploadtimes
                + ", addcopytimes=" + addcopytimes + "]";
    }
    
}
