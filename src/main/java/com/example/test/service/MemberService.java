package com.example.test.service;

import com.example.test.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    public int deleteMemberByTeam(Integer TeamID){
        return memberMapper.deleteByTeam(TeamID);
    }

}
