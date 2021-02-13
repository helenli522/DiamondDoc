package com.example.test.mapper;

import com.example.test.bean.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
public interface TeamMapper {

    //添加团队
    @Options(useGeneratedKeys = true,keyProperty = "TeamID")
    @Insert("insert into Team(UserID,TeamName,MemberNumber,Privilege,TeamInfo) values(#{UserID},#{TeamName},#{MemberNumber},#{Privilege},#{TeamInfo})")
    public int insertTeam(Team team);

    //删除团队
    @Delete("delete from Team where TeamID=#{TeamID}")
    public int deleteTeamById(Integer TeamID);

    //修改团队名称
    @Update("update Team set TeamName=#{TeamName} where TeamID=#{TeamID}")
    public int updateName(Team team);

    //修改团队人数+1
    @Update("update Team set MemberNumber=MemberNumber+1 where TeamID=#{TeamID}")
    public int upNum(Integer TeamID);

    //修改团队人数-1
    @Update("update Team set MemberNumber=MemberNumber-1 where TeamID=#{TeamID}")
    public int downNum(Integer TeamID);

    //修改团队权限
    @Update("update Team set Privilege=#{Privilege} where TeamID=#{TeamID}")
    public int updatePri(Team team);

    //修改团队简介
    @Update("update Team set TeamInfo=#{TeamInfo} where TeamID=#{TeamID}")
    public int updateInf(Team team);

    //根据id查找团队
    @Select("select * from Team where TeamID=#{id}")
    public Team getTeamById(Integer id);

    //根据创建者查找团队
    @Select("select * from Team where UserID=#{UserID}")
    public List<Team> getTeamByUser(Integer UserID);

    //根据名称查找团队
    @Select("select * from Team where TeamName=#{name}")
    public Team getTeamByName(String name);
}
