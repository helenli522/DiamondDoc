package com.example.test.mapper;

import com.example.test.bean.Collect;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectMapper {

    @Select("select * from Collect where CollectID=#{CollectID}")
    public Collect getCollectById(Integer CollectID);

    @Select("select * from Collect where UserID=#{UserID} order by DateTime desc")
    public List<Collect> getCollectByUser(Integer UserID);

    @Select("select * from Collect where DocID=#{DocID} and UserID=#{UserID}")
    public Collect getCollectByDocAndUser(Integer DocID, Integer UserID);

    @Select("select count(*) from Collect where DocID=#{DocID}")
    public Integer getSum(Integer DocID);

    @Options(useGeneratedKeys = true,keyProperty = "CollectID")
    @Insert("insert into Collect(UserID,DocID) values(#{UserID},#{DocID})")
    public int insertCollect(Collect collect);

    @Delete("delete from Collect where CollectID=#{CollectID}")
    public int deleteById(Integer CollectID);

    @Delete("delete from Collect where UserID=#{UserID}")
    public int deleteByUser(Integer UserID);

    @Delete("delete from Collect where DocID=#{DocID}")
    public int deleteByDoc(Integer DocID);

    @Delete("delete from Collect where DocID=#{DocID} and UserID=#{UserID}")
    public int deleteByDocAndUser(Integer DocID, Integer UserID);
}
