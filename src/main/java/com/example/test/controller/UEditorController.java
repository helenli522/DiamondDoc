package com.example.test.controller;

import com.example.test.bean.UEditorFile;
import com.example.test.bean.UEditorUpload;
import com.example.test.common.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/ueditor")
public class UEditorController {
    /*@Autowired
    private UEditorUpload uEditorUpload;

    @RequestMapping("/config")
    public String config(HttpServletRequest request, HttpServletResponse response, String action, MultipartFile upfile)throws IOException{
        if(action.equals("config")){
            log.info("UEditor后端配置");
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type","text/html");
            String path =ClassUtils.getDefaultClassLoader().getResource("").getPath()+"config";

            PrintWriter filterWriter=response.getWriter();
            filterWriter.write(new ActionEnter(request,path).exec());
            filterWriter.flush();
            filterWriter.close();
        }else  if(action.equals("ueditorImage")){
            log.info("UEditor上传图片");
            UEditorFile editorFile=uEditorUpload.uploadImage(upfile);
            JSONObject jsonObject=new JSONObject(editorFile);
            return jsonObject.toString();
        }
        return null;
    }*/
    @RequestMapping("/config")
    @ResponseBody
    public String exec(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getRealPath("/");
        return new ActionEnter( request, rootPath ).exec();
    }
}
