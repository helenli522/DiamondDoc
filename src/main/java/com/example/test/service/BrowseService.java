package com.example.test.service;

import com.example.test.bean.Browse;
import com.example.test.mapper.BrowseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrowseService {

    @Autowired
    BrowseMapper browseMapper;

    //添加浏览记录
    public Browse insertBrowse(Browse browse){
        Browse browse1 = browseMapper.getBrowseByDocAndUser(browse.DocID,browse.UserID);
        if(browse1 != null){
            browseMapper.updateDateTime(browse1.getBrowseID());
            return browse1;
        }
        else{
            browseMapper.insertBrowse(browse);
            return browseMapper.getBrowseById(browse.getBrowseID());
        }
    }

    //删除
    public int deleteBrowseById(Integer BrowseID){
        return browseMapper.deleteBrowseById(BrowseID);
    }

    //删除用户所有浏览记录
    public int deleteBrowseByUser(Integer UserID){
        return browseMapper.deleteBrowseByUser(UserID);
    }

    //删除文档所有浏览记录
    public int deleteBrowseByDoc(Integer DocID){
        return browseMapper.deleteBrowseByDoc(DocID);
    }

    public int deleteBrowseByDocAndUser(Integer DocID,Integer UserID){
        return browseMapper.deleteBrowseByDocAndUser(DocID, UserID);
    }

    //查看用户所有浏览记录
    public List<Browse> getBrowseByUser(Integer UserID){
        return browseMapper.getBrowseByUser(UserID);
    }

    //查看指定浏览记录
    public Browse getBrowseById(Integer BrowseID){
        return browseMapper.getBrowseById(BrowseID);
    }

    //更新浏览时间
    public int updateDateTime(Integer BrowseID){
        return browseMapper.updateDateTime(BrowseID);
    }

}
