package com.example.test.controller;

import com.example.test.bean.*;
import com.example.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TeamController {

    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    @Autowired
    MemberService memberService;
    @Autowired
    DocService docService;
    @Autowired
    BrowseService browseService;
    @Autowired
    CollectService collectService;
    @Autowired
    CommentService commentService;
    @Autowired
    EditService editService;


    @PostMapping("/team/addTeam")
    public CommonResult addTeam(@RequestBody Team team) {
        System.out.println(team.getUserID());
        System.out.println(team.getTeamName());
        Team result = teamService.insertTeam(team);
        return new CommonResult(200, null, result);
    }


    @PostMapping("/team/getTeam")
    public Team getTeam(@RequestBody Integer TeamID) {
        return teamService.getTeamById(TeamID);
    }

    @PostMapping("/team/inTeams")
    public List<TeamShow> inTeams(@RequestBody Integer UserID) {
        List<Team> teams = teamService.getTeamByUser(UserID);
        if (teams == null)
            return null;
        List<TeamShow> teamShows = new ArrayList<>();
        for (Team team : teams) {
            teamShows.add(new TeamShow(team.getTeamID(), team.getUserID(), team.getTeamName(), userService.getUserById(team.getUserID()).getUserName()));
        }
        return teamShows;
    }

    @PostMapping("/team/quit/{TeamID}")
    public CommonResult quit(@PathVariable("TeamID") Integer TeamID, @RequestBody String UserName) {
        teamService.deleteByTeamAndUser(TeamID, userService.getUserByName(UserName).getUserID());
        return new CommonResult(200, null, null);
    }

    @PostMapping("/team/delete/{TeamID}")
    public CommonResult delete(@PathVariable("TeamID") Integer TeamID) {
        Team team = teamService.getTeamById(TeamID);
        if(team == null){
            return new CommonResult(500,"cannot find the team!",null);
        }
        memberService.deleteMemberByTeam(TeamID);
        int flag = teamService.deleteTeam(TeamID);
        if(flag == 0){
            return new CommonResult(400,"failure!",null);
        }
        else{
            return new CommonResult(200,"success!",null);
        }
    }

    @PostMapping("/team/updateInfo/{TeamID}")
    public CommonResult updateInfo(@PathVariable("TeamID") Integer TeamID, @RequestBody String TeamInfo) {
        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        Team team=teamService.getTeamById(TeamID);
        team.setTeamInfo(TeamInfo);
        teamService.updateInfo(team);
        return new CommonResult(200, null, null);
    }

    @PostMapping("/team/addMember")
    public CommonResult addMember(@RequestBody Member member) {
        if (member.getUserID() == teamService.getTeamById(member.getTeamID()).getUserID()) {
            return new CommonResult(500, "Leader can't be added as a member!", null);
        }
        Member result = teamService.insertMember(member);
        return new CommonResult(200, null, result);
    }

    @PostMapping("/team/getInfo/{TeamID}")
    public String getInfo(@PathVariable("TeamID") Integer TeamID) {
        return teamService.getTeamById(TeamID).getTeamInfo();
    }

    @PostMapping("/team/getUser/{TeamID}")
    public MemberShow getUser(@PathVariable("TeamID") Integer TeamID) {
        User user=userService.getUserById(teamService.getTeamById(TeamID).getUserID());
        return new MemberShow(user.getProfileUrl(),user.getUserName());
    }
    @PostMapping("/team/getMember/{TeamID}")
    public List<MemberShow> getMember(@PathVariable("TeamID") Integer TeamID) {
        List<Member> members=teamService.getMemberByTeam(TeamID);
        List<MemberShow> memberShows=new ArrayList<>();
        for(Member member:members){
            memberShows.add(new MemberShow(userService.getUserById(member.getUserID()).getProfileUrl(),userService.getUserById(member.getUserID()).getUserName()));
        }
        return memberShows;
    }

    @PostMapping("/team/getDoc/{TeamID}")
    public List<Document> getDoc(@PathVariable("TeamID") Integer TeamID){
        return docService.getTeamDoc(TeamID);
    }

    @PostMapping("/team/deleteDoc/{TeamID}")
    public CommonResult deleteDoc(@PathVariable("TeamID") Integer TeamID,@RequestBody Integer DocID){
        Document document=docService.getDocById(DocID);
        if(document == null){
            return new CommonResult(500,"cannot find the document",null);
        }
        else {
            browseService.deleteBrowseByDoc(DocID);
            collectService.deleteByDoc(DocID);
            commentService.deleteByDoc(DocID);
            editService.deleteByDoc(DocID);
            if(document.getTeam()==TeamID && document.getIsTeam()==1){
                docService.deleteDocById(DocID,document.getUserID());
                return new CommonResult(200,null,null);
            }else {
                return new CommonResult(500,"Not a team document!",null);
            }
        }
    }
}
