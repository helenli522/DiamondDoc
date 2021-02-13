package com.example.test.bean;

public class TeamShow {
    Integer TeamID;
    Integer LeaderID;
    String TeamName;
    String LeaderName;

    public TeamShow(Integer teamID, Integer leaderID, String teamName, String leaderName) {
        TeamID = teamID;
        LeaderID = leaderID;
        TeamName = teamName;
        LeaderName = leaderName;
    }

    public Integer getTeamID() {
        return TeamID;
    }

    public void setTeamID(Integer teamID) {
        TeamID = teamID;
    }

    public Integer getLeaderID() {
        return LeaderID;
    }

    public void setLeaderID(Integer leaderID) {
        LeaderID = leaderID;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getLeaderName() {
        return LeaderName;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
    }
}
