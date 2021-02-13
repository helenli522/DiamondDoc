package com.example.test.controller;

import com.example.test.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {
    @Autowired
    TemplateService templateService;

    @PostMapping("/template/getContent/{TemplateID}")
    public String getContent(@PathVariable("TemplateID") Integer TemplateID){
        return  templateService.getTemplateByTemplateID(TemplateID).getContent();
    }
}

