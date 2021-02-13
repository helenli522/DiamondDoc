package com.example.test.service;

import com.example.test.bean.Member;
import com.example.test.bean.Team;
import com.example.test.bean.User;
import com.example.test.mapper.MemberMapper;
import com.example.test.mapper.TeamMapper;
import com.example.test.mapper.UserMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService{

    @Autowired
    TeamMapper teamMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MemberMapper memberMapper;

    public Team insertTeam(Team team){
        if(team == null || team.getTeamName() == null || team.getMemberNumber() == null || team.getPrivilege() == null){
            return null;
        }
        Integer UserID = team.getUserID();
        User user = userMapper.getUserById(UserID);;
        if(UserID != null && user == null){
            return null;
        }
        teamMapper.insertTeam(team);
        return teamMapper.getTeamById(team.getTeamID());
    }

    public Member insertMember(Member member){
        Integer TeamID = member.getTeamID();
        Integer UserID = member.getUserID();
        Member member1 = memberMapper.getMemberByTeamAndUser(TeamID,UserID);
        if(member1 != null){
            return member1;
        }
        Team team = teamMapper.getTeamById(TeamID);
        User user = userMapper.getUserById(UserID);
        if(team == null || user == null){
            return null;
        }
        memberMapper.insertMember(member);
        return memberMapper.getMemberById(member.getMemberID());
    }

    //删除成员,用于成员退出或者被移出
    public void deleteByTeamAndUser(Integer TeamID,Integer UserID){
        memberMapper.deleteByTeamAndUser(TeamID, UserID);
        teamMapper.downNum(TeamID);
    }

    //删除团队
    public int deleteTeam(Integer TeamID){
        return teamMapper.deleteTeamById(TeamID);
    }

    public int updateTeamName(Team team,Integer userID){
        Team team1 = teamMapper.getTeamById(team.getTeamID());
        if(team1.getUserID() == userID){
            teamMapper.updateName(team);
            return 1;
        }
        return 0;
    }

    public int updatePri(Team team,Integer userID){
        Team team1 = teamMapper.getTeamById(team.getTeamID());
        if(team1.getUserID() == userID){
            teamMapper.updatePri(team);
            return 1;
        }
        return 0;
    }

    public int updateInfo(Team team){

            teamMapper.updateInf(team);
            return 1;
    }

    public Team getTeamById(Integer id){
        return teamMapper.getTeamById(id);
    }

    public Team getTeamByName(String name){
        return teamMapper.getTeamByName(name);
    }

    public List<Team> getCreateTeamByUser(Integer UserID){
        return teamMapper.getTeamByUser(UserID);
    }

    public List<Team> getTeamByUser(Integer UserID){
        List<Team> teams1 = teamMapper.getTeamByUser(UserID);
        List<Team> teams = new ArrayList<Team>();
        List<Member> members = memberMapper.getMemberByUser(UserID);
        if(members!= null && members.size() != 0){
            for(Member member : members){
                Integer TeamID = member.getTeamID();
                Team team = teamMapper.getTeamById(TeamID);
                teams.add(team);
            }
            if(teams != null && teams.size() > 0) {
                for (Team team : teams) {
                    teams1.add(team);
                }
            }
        }
        return teams1;
    }

    public List<Member> getMemberByTeam(Integer TeamID){
        return memberMapper.getMemberByTeam(TeamID);
    }
}
