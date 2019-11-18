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

    @Autowired
//    @Resource
    private MultipleDataSourceService multipleDataSourceService;

    @PostMapping("/insertAll")
    public MessageResult<Void> insertAll(@RequestBody UserJobVM userJobVM) {
        return multipleDataSourceService.insertAll(userJobVM.getUser(),userJobVM.getJob());
    }

}
