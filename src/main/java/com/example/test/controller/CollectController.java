package com.example.test.controller;

import com.example.test.bean.Collect;
import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.CollectService;
import com.example.test.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CollectController {

    @Autowired
    CollectService collectService;
    @Autowired
    DocService docService;

    @PostMapping("/collect/getCollect")
    public List<Document> getCollect(@RequestBody Integer UserID){
        List<Collect> collectList = collectService.getCollectByUser(UserID);
        List<Document> documentList = new ArrayList<Document>();
        if(collectList == null || collectList.size() == 0){
            return null;
        }
        for(Collect collect : collectList){
            Integer DocID = collect.getDocID();
            Document document = docService.getDocById(DocID);
            documentList.add(document);
        }
        return documentList;
    }

    @PostMapping("/collect/collected")
    public CommonResult collected(@RequestBody Collect collect){
        Collect collect1 = collectService.getCollectByDocAndUser(collect.DocID, collect.UserID);
        if(collect1 != null){
            return new CommonResult(200,"collected",null);
        }
        else{
            return new CommonResult(200,"not collected",null);
        }
    }

    @PostMapping("/collect/insertCollect")
    public CommonResult insertCollect(@RequestBody Collect collect){
        Collect collect1 = collectService.insertCollect(collect);
        return new CommonResult(200,null,collect1);
    }

    @PostMapping("/collect/deleteCollect")
    public CommonResult deleteCollect(@RequestBody Collect collect){
        if(collect == null){
            return new CommonResult(500,"collect is null",null);
        }
        int flag = collectService.deleteByDocAndUser(collect.getDocID(),collect.getUserID());
        if(flag == 0){
            return new CommonResult(400,"failure",null);
        }
        else{
            return new CommonResult(200,"success",null);
        }
    }

    @PostMapping("/collect/collectNum")
    public int getCollectNumber(@RequestBody Integer DocID){
        return collectService.getSum(DocID);
    }
}
