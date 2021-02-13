package com.example.test.bean;

import java.sql.Date;

public class News {
    public Integer NewsID;
    private Integer UserID;
    private Date DateTime;
    private Integer Type;
    private Integer IsRead;
    private String Content;

    public Integer getIsRead() {
        return IsRead;
    }

    public void setIsRead(Integer isRead) {
        IsRead = isRead;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Integer getNewsID() {
        return NewsID;
    }

    public void setNewsID(Integer newsID) {
        NewsID = newsID;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
