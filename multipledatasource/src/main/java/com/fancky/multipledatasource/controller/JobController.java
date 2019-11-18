package com.fancky.multipledatasource.controller;

import com.fancky.multipledatasource.model.entity.test.Job;
import com.fancky.multipledatasource.model.viewmodel.MessageResult;
import com.fancky.multipledatasource.service.test.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
//    @Resource
    private JobService jobService;

    @GetMapping("")
    public String index()
    {
        return "hello world";
    }

    @PostMapping("/insertJob")
    public MessageResult<Void> insertJob(@RequestBody Job job) {
        return jobService.insert(job);
    }

}
