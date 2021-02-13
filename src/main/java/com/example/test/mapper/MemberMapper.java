package com.example.test.mapper;

import com.example.test.bean.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
public interface MemberMapper {

    @Select("select * from Member where MemberID=#{MemberID}")
    public Member getMemberById(Integer MemberID);

    //用户所有加入
    @Select("select * from Member where UserID=#{UserID}")
    public List<Member> getMemberByUser(Integer UserID);

    @Select("select * from Member where TeamID=#{TeamID} and UserID=#{UserID}")
    public Member getMemberByTeamAndUser(Integer TeamID,Integer UserID);

    @Select("select * from Member where TeamID=#{TeamID}")
    public List<Member> getMemberByTeam(Integer TeamID);

    //添加成员
    @Options(useGeneratedKeys = true,keyProperty = "MemberID")
    @Insert("insert into Member(TeamID,UserID) values(#{TeamID},#{UserID})")
    public int insertMember(Member member);

    //删除成员
    @Delete("delete from Member where UserID=#{UserID} and TeamID=#{TeamID}")
    public int deleteByTeamAndUser(Integer TeamID,Integer UserID);

    @Delete("delete from Member where TeamID=#{TeamID}")
    public int deleteByTeam(Integer TeamID);
}
