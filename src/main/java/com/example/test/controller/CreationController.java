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

import java.util.List;

@RestController
public class CreationController {

    @Autowired
    DocService docService;
    @Autowired
    CollectService collectService;

    @PostMapping("/created/getDocument")
    public List<Document> getDocument(@RequestBody Integer UserID){
        return docService.getExistDocByUser(UserID);
    }

    @PostMapping("/created/collected")
    public CommonResult collected(@RequestBody Collect collect){
        if(collect == null){
            return new CommonResult(500,"collect is null",null);
        }
        Collect collect1 = collectService.getCollectByDocAndUser(collect.DocID, collect.UserID);
        if(collect1 != null){
            return new CommonResult(200,"yes",null);
        }
        else{
            return new CommonResult(200,"no",null);
        }
    }

    @PostMapping("/created/insertCollect")
    public CommonResult insertCollect(@RequestBody Collect collect){
        Collect collect1 = collectService.insertCollect(collect);
        return new CommonResult(200,null,collect1);
    }

    @PostMapping("/created/deleteCollect")
    public CommonResult deleteCollect(@RequestBody Collect collect){
        if(collect == null){
            return new CommonResult(500,null,null);
        }
        int flag = collectService.deleteByDocAndUser(collect.DocID, collect.UserID);
        if(flag == 0){
            return new CommonResult(400,"deleteCollect failure",null);
        }
        else {
            return new CommonResult(200,"deleteCollect success",null);
        }
    }

//    @GetMapping("created/delete")
//    public CommonResult delete(Collect collect){
//        if(collect == null || collect.getDocID() == null || collect.getUserID() == null){
//            return new CommonResult(500,null,null);
//        }
//        int flag = docService.recycleDoc(collect.getDocID(),collect.getUserID());
//        if(flag == 2)
//            return new CommonResult(500,"document does not exist",null);
//        else if(flag == 1)
//            return new CommonResult(400,"you are not the creator",null);
//        else
//            return new CommonResult(200,"delete success",null);
//    }

    @PostMapping("/created/deleteDocument")
    public CommonResult deleteDocument(@RequestBody Collect collect){
        if(collect == null || collect.DocID == null || collect.UserID == null){
            return new CommonResult(500,null,null);
        }
        int flag = docService.recycleDoc(collect.DocID,collect.UserID);
        if(flag == 0){
            return new CommonResult(400,"deleteDocument failure",null);
        }
        else{
            return new CommonResult(200,"deleteDocument success",null);
        }
    }
}
