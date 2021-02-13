package com.example.test.controller;

import com.example.test.bean.Comment;
import com.example.test.bean.CommentShow;
import com.example.test.bean.CommonResult;
import com.example.test.service.CommentService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

@Autowired
    UserService userService;

    @GetMapping("/comment")
    public Comment insertComment(Comment comment){
        return commentService.insertComment(comment);
    }


    @PostMapping("/comment/add")
    public CommonResult addComment(@RequestBody Comment comment){
        Comment result=commentService.insertComment(comment);
        if(result!=null)
            return new CommonResult(200,null,result);
        else
            return new CommonResult(500,"Failed",null);
    }

    @PostMapping("/comment/commentList/{DocID}")
    public List<CommentShow> getCommentList(@PathVariable Integer DocID){
        List<Comment> result=commentService.getCommentByDoc(DocID);
        List<CommentShow> commentShows=new ArrayList<>();
        for(Comment comment:result){
            commentShows.add(new CommentShow(userService.getUserById(comment.getUserID()).getProfileUrl(),userService.getUserById(comment.getUserID()).getUserName(),comment.getContent(),comment.getDateTime()));
        }
        return commentShows;
    }
}
