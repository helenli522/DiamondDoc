package com.example.test.service;

import com.example.test.bean.Document;
import com.example.test.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocService {

    @Autowired
    DocumentMapper documentMapper;

    //新建文档
    public Document insertDoc(Document document){
        documentMapper.insertDoc(document);
        return documentMapper.getDocById(document.getDocID());
    }

    //根据Id获取文档
    public Document getDocById(Integer docID){
        return documentMapper.getDocById(docID);
    }

    //根据用户获取文档
    public List<Document> getDocByUser(Integer userID){
        return documentMapper.getDocByUser(userID);
    }

    public List<Document> getRecycleByUser(Integer UserID){
        return documentMapper.getRecycleByUser(UserID);
    }

    public List<Document> getExistDocByUser(Integer UserID){
        return documentMapper.getExistDocByUser(UserID);
    }
    public  List<Document> getTeamDoc(Integer TeamID){
        return documentMapper.getTeamDoc(TeamID);
    }

    //删除文档,只有创建者有权限
    public int deleteDocById(Integer docID,Integer userID){
        Document document = documentMapper.getDocById(docID);
        if(document.getUserID() == userID){
            documentMapper.deleteDocById(docID);
            return 1;
        }
        return 0;
    }

    public List<Document> getAllRecycle(Integer UserID){
        List<Document> documentList = documentMapper.getRecycleByUser(UserID);
        return documentList;
    }

    //修改权限信息，只有创建者有权限
    public int updatePri(Document document, Integer userID){
        Document document1 = documentMapper.getDocById(document.getDocID());
        if(userID == document1.getUserID()){
            documentMapper.updatePri(document);
            return 1;
        }
        return 0;
    }

    //回收文档，只有创建者有权限
    public int updateRec(Document document, Integer userID){
        Document document1 = documentMapper.getDocById(document.getDocID());
        if(document1.getUserID() == userID){
            documentMapper.updateRec(document);
            return 1;
        }
        return 0;
    }

    //放到回收站
    public int recycleDoc(Integer DocID,Integer UserID){
        Document document = documentMapper.getDocById(DocID);
        if(document == null){
            return 2;
        }
        if(document.getUserID() == UserID){
            documentMapper.recycleDoc(DocID);
            return 0;
        }
        return 1;
    }

    //从回收站恢复
    public int recoverDoc(Integer DocID,Integer UserID){
        Document document = documentMapper.getDocById(DocID);
        if(document.getUserID() == UserID){
            return documentMapper.recoverDoc(DocID);
        }
        return 0;
    }

    //修改团队信息，只有创建者有权限
    public int updateTea(Document document, Integer userID){
        Document document1 = documentMapper.getDocById(document.getDocID());
        if(document1.getUserID() == userID){
            documentMapper.updateTea(document);
            return 1;
        }
        return 0;
    }

    //修改编辑状态
    public int updateEdi(Document document){
        return documentMapper.updateEdi(document);
    }

    public int updateCon(Document document){
        return documentMapper.updateCon(document);
    }

    public int updateTitle(Document document){
        return documentMapper.updateTitle(document);
    }

    //文档被评论
    public int commentDoc(Document document){
        return documentMapper.commentDoc(document.getDocID());
    }

    //文档被收藏
    public int collectDoc(Document document){
        return documentMapper.collectDoc(document.getDocID());
    }
}
