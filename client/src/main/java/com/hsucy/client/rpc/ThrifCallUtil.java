package com.hsucy.client.rpc;

import com.hsucy.client.configuration.ThriftClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThrifCallUtil {

    
    private static ThriftClient thriftClient;

    @Autowired
    public ThrifCallUtil(ThriftClient thriftClient) {
        ThrifCallUtil.thriftClient = thriftClient;
    }


    public static synchronized Result callRemoteService(String identifier, String methodName, String argTypes, String argValues) {

        try {
            System.out.println(thriftClient);
            thriftClient.open();
            String id = thriftClient.getRPCThriftService().getId(123);
            return Result.getSuccessResult("string", id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.getFailResult("触发远程调用失败");
        } finally {
            thriftClient.close();
        }


    }


    
}