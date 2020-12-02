package com.fancky.multipledatasource.controller;

import com.fancky.multipledatasource.model.entity.demo.User;
import com.fancky.multipledatasource.model.viewmodel.MessageResult;
import com.fancky.multipledatasource.model.viewmodel.UserJobVM;
import com.fancky.multipledatasource.service.MultipleDataSourceService;
import com.fancky.multipledatasource.service.test.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MultipleDataSourceController {

    /*
     * 分布式数据库：部署在多个服务器上。
     */

    /*
     *atomikos:支持的是同一项目的多个数据库连接的事务，也就支持了分布式数据库（多个服务器上的数据库）。
     *         但是没找到它支持分布式应用程序的多连接数据库事务，即多个应用程序内的事务。如：MicroServiceA
     *         更新了DB-A又调用MicroServeB更新DB-B。
     *
     */

    /*
     *分布式应用程序事务解决方案。
     * 一、TCC：可以做到严格的数据正确性。但是并发量不高。
     * 二、利用消息中间件实现高并发的异步事务。但是难100%做到数据正确性。单通过人工手动回滚事务可以做到正确性。
     *     此设计思路见：SpringBootProject项目的DistributedTranscationController
     */

    @Autowired
//    @Resource
    private MultipleDataSourceService multipleDataSourceService;

    @PostMapping("/insertAll")
    public MessageResult<Void> insertAll(@RequestBody UserJobVM userJobVM) {
        return multipleDataSourceService.insertAll(userJobVM.getUser(), userJobVM.getJob());
    }

}
