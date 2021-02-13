package com.example.test.bean;

import java.sql.Date;

public class CommentShow {
    public String ProfileUrl;
    public String UserName;
    public String Content;
    public Date DateTime;

    public CommentShow(String profileUrl, String userName, String content, Date dateTime) {
        ProfileUrl = profileUrl;
        UserName = userName;
        Content = content;
        DateTime = dateTime;
    }

    public String getProfileUrl() {
        return ProfileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        ProfileUrl = profileUrl;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }
}
