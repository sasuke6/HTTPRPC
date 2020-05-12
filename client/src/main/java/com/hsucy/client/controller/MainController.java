package com.hsucy.client.controller;

import com.hsucy.client.remoteservice.UserService;
import com.hsucy.client.rpc.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/getUserCount")
    public Result getUserCount() {
        Integer userCount = userService.getUserCount();
        return Result.getSuccessResult("Integer", userCount.toString());
    }

    @RequestMapping("/getResult")
    public Result requestMethodName() {
        return new Result(true, "请求成功", "type", "value");
    }
    
}