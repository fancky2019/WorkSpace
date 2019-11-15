package com.fancky.multipledatasource.controller;

import com.fancky.multipledatasource.model.entity.demo.User;
import com.fancky.multipledatasource.model.viewmodel.MessageResult;
import com.fancky.multipledatasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
//    @Resource
    private UserService userService;

    @GetMapping("")
    public String index()
    {
        
        return "hello world";
    }
    @PostMapping("/insertUser")
    public MessageResult<Void> insertUser(@RequestBody User user) {
        return userService.insert(user);
    }

}
