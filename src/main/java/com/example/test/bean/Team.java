package com.example.test.bean;

import java.util.Date;

public class Team {
    private Integer TeamID;
    private Integer UserID;
    private String TeamName;
    private Integer MemberNumber;
    private Integer Privilege;
    private Date DateTime;
    private String TeamInfo;

    public String getTeamInfo() {
        return TeamInfo;
    }

    public void setTeamInfo(String teamInfo) {
        TeamInfo = teamInfo;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Integer getTeamID() {
        return TeamID;
    }

    public void setTeamID(Integer teamID) {
        TeamID = teamID;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public Integer getMemberNumber() {
        return MemberNumber;
    }

    public void setMemberNumber(Integer memberNumber) {
        MemberNumber = memberNumber;
    }

    public Integer getPrivilege() {
        return Privilege;
    }

    public void setPrivilege(Integer privilege) {
        Privilege = privilege;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }
}
