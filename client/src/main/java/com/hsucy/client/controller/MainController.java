package com.hsucy.client.controller;

import com.hsucy.client.configuration.ThriftClient;
import com.hsucy.client.remoteservice.UserService;
import com.hsucy.client.remoteservice.UserServiceInf;
import com.hsucy.client.rpc.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserServiceInf userServiceInf;

    @Autowired
    private ThriftClient thriftClient;
    
    @RequestMapping("/getUserCount")
    public Result getUserCount() {
        Integer userCount = userService.getUserCount();
        return Result.getSuccessResult("Integer", userCount.toString());
    }

    @RequestMapping("/getResult")
    public Result requestMethodName() {
        return new Result(true, "请求成功", "type", "value");
    }

    @RequestMapping("/getId")
    public Result getUserId() throws Exception {
        // System.out.println(thriftClient);
        // Object userId = userServiceInf.getId();
        System.out.println(userServiceInf.getId());
        // thriftClient.open();
        // String userId = thriftClient.getRPCThriftService().getId(123);
        return Result.getSuccessResult("String", "123");
    }
    
}