package com.example.test.bean;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class UEditorUpload {
    @Value("imgUrl")
    private  String URL;
    private Log log = LogFactory.getLog(UEditorUpload.class);
    private String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public UEditorFile uploadImage(MultipartFile file) throws IOException {
        log.info("UEditor开始上传文件");
        String fileName = file.getOriginalFilename();  //获取文件名
        //Ueditor的config.json规定的返回路径格式
        String returnPath = "/image/ueditor/"+new Date().getTime()+"/"+fileName;
        File saveFile = new File(path+"static"+returnPath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        file.transferTo(saveFile);  //将临时文件移动到保存路径
        log.info("UEditor上传文件成功，保存路径："+saveFile.getAbsolutePath());
        UEditorFile uEditorFile = new UEditorFile();
        uEditorFile.setState("SUCCESS");
        uEditorFile.setUrl(returnPath);  //访问URL
        uEditorFile.setTitle(fileName);
        uEditorFile.setOriginal(fileName);
        return uEditorFile;
    }

}
