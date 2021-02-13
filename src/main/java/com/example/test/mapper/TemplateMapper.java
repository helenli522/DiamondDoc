package com.example.test.mapper;

import com.example.test.bean.Template;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
public interface TemplateMapper {
    //创建模板
    @Options(useGeneratedKeys = true,keyProperty = "TemplateID")
    @Insert("insert into Template(TemplateID,TemplateName,Content,Picture) values(#{TemplateID},#{TemplateName},#{Content},#{Picture})")
    Template CreateNews(Template template);

    //根据TemplateID查看消息
    @Select("select * from Template where TemplateID=#{TemplateID}")
    Template getTemplateByTemplateID(Integer TemplateID);
}
