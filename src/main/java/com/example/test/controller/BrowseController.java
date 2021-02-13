package com.example.test.controller;

import com.example.test.bean.Browse;
import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.BrowseService;
import com.example.test.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BrowseController {

    @Autowired
    BrowseService browseService;
    @Autowired
    DocService docService;

    @PostMapping("/browse/getBrowse")
    public List<Document> getBrowse(@RequestBody Integer UserID){
        List<Document> documentList = new ArrayList<Document>();
        List<Browse> browseList = browseService.getBrowseByUser(UserID);
        if(browseList == null || browseList.size()==0){
            return null;
        }
        for(Browse browse : browseList){
            Integer DocID = browse.getDocID();
            Document document =docService.getDocById(DocID);
            documentList.add(document);
        }
        return documentList;
    }

    @PostMapping("/browse/insertBrowse")
    public CommonResult insertBrowse(@RequestBody Browse browse){
        //判断是否重复的业务逻辑已经在BrowseService实现
        Browse browse1 = browseService.insertBrowse(browse);
        return new CommonResult(200,null,browse1);
    }

    @PostMapping("/browse/updateBrowse")
    public CommonResult updateBrowse(@RequestBody Integer BrowseID){
        browseService.updateDateTime(BrowseID);
        Browse browse = browseService.getBrowseById(BrowseID);
        return new CommonResult(200,null,browse);
    }

    @PostMapping("/browse/deleteBrowseById")
    public CommonResult deleteBrowseById(@RequestBody Integer BrowseID){
        browseService.deleteBrowseById(BrowseID);
        return new CommonResult(200,null,null);
    }

    @PostMapping("/browse/deleteBrowseByUser")
    public CommonResult deleteBrowseByUser(@RequestBody Integer UserID){
        browseService.deleteBrowseByUser(UserID);
        return new CommonResult(200,null,null);
    }

    @PostMapping("/browse/deleteBrowse")
    public CommonResult deleteBrowse(@RequestBody Browse browse){
        if(browse == null){
            return new CommonResult(400,"failure",null);
        }
        int flag = browseService.deleteBrowseByDocAndUser(browse.DocID,browse.UserID);
        if(flag == 0){
            return new CommonResult(400,"failure",null);
        }
        else{
            return new CommonResult(200,"success",null);
        }
    }
}
