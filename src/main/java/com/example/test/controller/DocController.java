package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.bean.Edit;
import com.example.test.mapper.DocumentMapper;
import com.example.test.service.DocService;
import com.example.test.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocController {

    @Autowired
    DocumentMapper documentMapper;
    @Autowired
    DocService docService;
    @Autowired
    EditService editService;

    @GetMapping("/doc/user/{UserID}")
    public List<Document> getDocByUser(@PathVariable("UserID") Integer UserID){
        return docService.getDocByUser(UserID);
    }

    @GetMapping("/doc")
    public Document insertDoc(Document document){
        return docService.insertDoc(document);
    }

    @GetMapping("/doc/update/c")
    public Document updateCon(Document document){
        documentMapper.updateCon(document);
        return document;
    }

    @GetMapping("/doc/update/p/{userID}")
    public Document updatePri(Document document, @PathVariable("userID") Integer userID){
        docService.updatePri(document,userID);
        return docService.getDocById(document.getDocID());
    }

    @GetMapping("/doc/update/r")
    public Document updateRec(Document document){
        documentMapper.updateRec(document);
        return document;
    }

    @GetMapping("/doc/update/t")
    public Document updateTea(Document document){
        documentMapper.updateTea(document);
        return document;
    }

    @GetMapping("/doc/update/e")
    public Document updateEdi(Document document){
        documentMapper.updateEdi(document);
        return docService.getDocById(document.getDocID());
    }

    @GetMapping("/doc/comment/{DocID}")
    public Document comment(@PathVariable("DocID") Integer DocID){
        documentMapper.commentDoc(DocID);
        return documentMapper.getDocById(DocID);
    }

    @GetMapping("/doc/collect/{DocID}")
    public Document collect(@PathVariable("DocID") Integer DocID){
        documentMapper.collectDoc(DocID);
        return documentMapper.getDocById(DocID);
    }


    @RequestMapping(value = "/doc",method = RequestMethod.POST)
    public CommonResult buildDoc(@RequestBody Document document){
        System.out.println(document.getTitle());
        System.out.println("user"+document.getUserID());
        Integer result=docService.insertDoc(document).getDocID();
        return new CommonResult(200,null,result);
    }

//    @PostMapping("/doc/edit")
//    public CommonResult edit(@RequestBody Document document){
//        Document result=docService.insertDoc(document);
//        return new CommonResult(200,null,result);
//    }

    @PostMapping("/doc/get/{DocID}")
    public CommonResult getDoc(@PathVariable("DocID") Integer DocID){
        return new CommonResult(200,null,docService.getDocById(DocID));
    }


    @PostMapping("/doc/checkPriView/{DocID}")
    public boolean checkPriView(@PathVariable Integer DocID,@RequestParam(name="userID")Integer UserID){
        if(UserID==docService.getDocById(DocID).getUserID())
            return true;
        else if(docService.getDocById(DocID).getPrivilege()/1000==1)
            return true;
        else
            return false;
    }



    @PostMapping("/doc/checkPriEdit/{DocID}")
    public boolean checkPriEdit(@PathVariable Integer DocID,@RequestBody Integer UserID){
        if(UserID==docService.getDocById(DocID).getUserID())
            return true;
        else if(docService.getDocById(DocID).getPrivilege()/100-(docService.getDocById(DocID).getPrivilege()/1000)*10==1)
            return true;
        else
            return false;
    }

    @PostMapping("/doc/checkPriComment/{DocID}")
    public boolean checkPriComment(@PathVariable Integer DocID,@RequestBody Integer UserID){
        if(UserID==docService.getDocById(DocID).getUserID())
            return true;
        else if(docService.getDocById(DocID).getPrivilege()/10-(docService.getDocById(DocID).getPrivilege()/100)*10==1)
            return true;
        else
            return false;
    }

    @PostMapping("/doc/checkPriShare/{DocID}")
    public boolean checkPriShare(@PathVariable Integer DocID,@RequestBody Integer UserID){
        if(UserID==docService.getDocById(DocID).getUserID())
            return true;
        else if(docService.getDocById(DocID).getPrivilege()-(docService.getDocById(DocID).getPrivilege()/10)*10==1)
            return true;
        else
            return false;
    }

    @PostMapping("/doc/isEditable/{DocID}")
    public boolean isEditable(@PathVariable Integer DocID){
        if(docService.getDocById(DocID).getEditable()==0){
            return false;
        }
        else
            return true;
    }

    @PostMapping("/doc/beginEdit/{DocID}")
    public CommonResult beginEdit(@PathVariable Integer DocID){
        Document document=docService.getDocById(DocID);
        document.setEditable(0);
        docService.updateEdi(document);
        return new CommonResult(200,null,null);
    }

    @PostMapping("/doc/endEdit/{DocID}")
    public CommonResult endEdit(@PathVariable Integer DocID){
        Document document=docService.getDocById(DocID);
        document.setEditable(1);
        docService.updateEdi(document);
        return new CommonResult(200,null,null);
    }

    @RequestMapping(value = "/doc/edit",method = RequestMethod.POST)
    public CommonResult editDoc(@RequestBody Document document){
        docService.updatePri(document,document.UserID);
        docService.updateEdi(document);
        docService.updateCon(document);
        docService.updateTitle(document);
        Edit edit=new Edit();
        edit.setDocID(document.DocID);
        edit.setUserID(document.UserID);
        editService.insertEdit(edit);
        return new CommonResult(200,null,null);
    }
}
