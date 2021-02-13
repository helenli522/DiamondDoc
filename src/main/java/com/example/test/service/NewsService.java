package com.example.test.service;

import com.example.test.bean.News;
import com.example.test.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    NewsMapper newsMapper;

    public News getNewsByNewsId(Integer NewsID){
        News news = newsMapper.getNewsByNewsId(NewsID);
        return news;
    }

    public List<News> getNewsByUserId(Integer UserID){
        List<News> news = newsMapper.getNewsByUserId(UserID);
        return news;
    }

    public int updateRead(News news){
        if(news == null){
            return 0;
        }
        return newsMapper.updateRead(news.getNewsID());
    }

    public int deleteById(News news){
        if(news == null || news.getNewsID() == null){
            return 0;
        }
        return newsMapper.deleteById(news);
    }

    public News insertNews(News news){
        if(news==null||news.getUserID()==null||news.getType()==null||news.getContent()==null){
            return  null;
        }
        newsMapper.CreateNews(news);
        return newsMapper.getNewsByNewsId(news.getNewsID());
    }

}
