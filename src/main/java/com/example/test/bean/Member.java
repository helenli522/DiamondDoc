package com.example.test.bean;

import java.util.Date;

public class Member {
    private Integer MemberID;
    private Integer TeamID;
    private Integer UserID;
    private Date Datetime;

    public Integer getMemberID() {
        return MemberID;
    }

    public void setMemberID(Integer memberID) {
        MemberID = memberID;
    }

    public Integer getTeamID() {
        return TeamID;
    }

    public void setTeamID(Integer teamID) {
        TeamID = teamID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Date getDatetime() {
        return Datetime;
    }

    public void setDatetime(Date datetime) {
        Datetime = datetime;
    }
}
