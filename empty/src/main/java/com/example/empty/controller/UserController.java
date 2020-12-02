package com.example.empty.controller;

import com.example.empty.model.entity.demo.Person;
import com.example.empty.model.viewmodel.MessageResult;
import com.example.empty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
//    @Resource
    private UserService userService;

    @GetMapping("")
    public String index() {
        return "hello world";
    }

    @PostMapping("/insertUser")
    public MessageResult<Void> insertUser(@RequestBody Person person) {
        return userService.insert(person);
    }

}

