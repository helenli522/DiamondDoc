package com.example.test.bean;

public class MemberShow {
    String ProfileUrl;
    String UserName;

    public MemberShow(String profileUrl, String userName) {
        ProfileUrl = profileUrl;
        UserName = userName;
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
}
