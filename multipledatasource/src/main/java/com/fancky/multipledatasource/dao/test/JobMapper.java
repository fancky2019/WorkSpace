package com.fancky.multipledatasource.dao.test;

import com.fancky.multipledatasource.model.entity.test.Job;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    Job selectByPrimaryKey(Integer id);


}