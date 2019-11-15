package com.fancky.multipledatasource.dao.demo;

import com.fancky.multipledatasource.model.entity.demo.User;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
 @Repository需要在Spring中配置扫描地址，然后生成Dao层的Bean才能被注入到Service层中。

 @Mapper不需要配置扫描地址，通过xml里面的namespace里面的接口地址，生成了Bean后注入到Service层中。
 */
//@Repository  //@MapperScan("com.example.demo.dao")
@Mapper
public interface UserMapper {
    int insert(User record);
}