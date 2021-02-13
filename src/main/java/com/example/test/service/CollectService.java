package com.example.test.service;

import com.example.test.bean.Collect;
import com.example.test.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectService {

    @Autowired
    CollectMapper collectMapper;

    public Collect insertCollect(Collect collect){
        Collect collect1 = collectMapper.getCollectByDocAndUser(collect.DocID,collect.UserID);
        if(collect1 != null){
            return collect1;
        }
        else{
            collectMapper.insertCollect(collect);
            return collectMapper.getCollectById(collect.getCollectID());
        }
    }

    public int deleteByDocAndUser(Integer DocID,Integer UserID){
        return collectMapper.deleteByDocAndUser(DocID, UserID);
    }

    public int deleteById(Integer CollectID){
        return collectMapper.deleteById(CollectID);
    }

    public int deleteByUser(Integer UserID){
        return collectMapper.deleteByUser(UserID);
    }

    public int deleteByDoc(Integer DocID){
        return collectMapper.deleteByDoc(DocID);
    }

    public List<Collect> getCollectByUser(Integer UserID){
        return collectMapper.getCollectByUser(UserID);
    }

    public Collect getCollectById(Integer CollectID){
        return collectMapper.getCollectById(CollectID);
    }

    public Collect getCollectByDocAndUser(Integer DocID, Integer UserID){
        if(DocID == null || UserID == null){
            return null;
        }
        return collectMapper.getCollectByDocAndUser(DocID,UserID);
    }

    public Integer getSum(Integer DocID){
        return collectMapper.getSum(DocID);
    }

}
