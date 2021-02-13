package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecycleController {

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

    @PostMapping("/recycle/getRecycle")
    public List<Document> getRecycle(@RequestBody Integer UserID){
        return docService.getRecycleByUser(UserID);
    }

    @PostMapping("/recycle/recover")
    public CommonResult recover(@RequestBody Document document){
        int flag = docService.recoverDoc(document.DocID,document.UserID);
        Document document1 = docService.getDocById(document.DocID);
        if(flag == 1){
            return new CommonResult(200,"recover success",document1);
        }
        else{
            return new CommonResult(200,"recover failure",document1);
        }
    }

    //这是后端用来测试的
    @GetMapping("/recycle/deleteDoc")
    public CommonResult deleteDoc(Document document){
        Integer DocID = document.getDocID();
        Document document1 = docService.getDocById(DocID);
        if(document1 == null){
            return new CommonResult(500,"cannot find the document!",null);
        }
        browseService.deleteBrowseByDoc(DocID);
        collectService.deleteByDoc(DocID);
        commentService.deleteByDoc(DocID);
        editService.deleteByDoc(DocID);
        int flag = docService.deleteDocById(DocID,document.getUserID());
        if(flag == 0){
            return new CommonResult(400,"failure!",null);
        }
        else{
            return new CommonResult(200,"success!",null);
        }
    }

    @PostMapping("/recycle/delete")
    public CommonResult delete(@RequestBody Document document){
        Integer DocID = document.DocID;
        Document document1 = docService.getDocById(DocID);
        if(document1 == null){
            return new CommonResult(500,"cannot find the document",null);
        }
        browseService.deleteBrowseByDoc(DocID);
        collectService.deleteByDoc(DocID);
        commentService.deleteByDoc(DocID);
        editService.deleteByDoc(DocID);
        int flag = docService.deleteDocById(DocID,document.UserID);
        if(flag == 0){
            return new CommonResult(400,"failure",null);
        }
        else{
            return new CommonResult(200,"success",null);
        }
    }

    @PostMapping("/recycle/deleteAll")
    public CommonResult deleteAll(@RequestBody Integer UserID){
        if(UserID == null){
            return new CommonResult(500,"UserID is null",null);
        }
        List<Document> documentList = docService.getAllRecycle(UserID);
        if(documentList == null || documentList.size() == 0){
            return new CommonResult(400,"no document to delete",null);
        }
        for(Document document : documentList){
            Integer DocID = document.getDocID();

            browseService.deleteBrowseByDoc(DocID);
            collectService.deleteByDoc(DocID);
            commentService.deleteByDoc(DocID);
            editService.deleteByDoc(DocID);

            docService.deleteDocById(DocID,UserID);
        }
        return new CommonResult(200,"success",null);
    }

    //这是后端用来测试的
    @GetMapping("/recycle/deleteAllRecycle")
    public CommonResult deleteAllRecycle(Integer UserID){
        if(UserID == null){
            return new CommonResult(500,"UserID is null!",null);
        }
        List<Document> documentList = docService.getAllRecycle(UserID);
        if(documentList == null || documentList.size() == 0){
            return new CommonResult(400,"no document to delete!",null);
        }
        for(Document document : documentList){
            Integer DocID = document.getDocID();
            System.out.println("现在删除依赖");
            browseService.deleteBrowseByDoc(DocID);
            collectService.deleteByDoc(DocID);
            commentService.deleteByDoc(DocID);
            editService.deleteByDoc(DocID);
            System.out.println("现在删除文档"+DocID);
            docService.deleteDocById(DocID,UserID);
        }
        return new CommonResult(200,"success!",null);
    }
}
