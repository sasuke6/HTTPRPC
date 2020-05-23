package com.hsucy.client.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceProxy<T> implements InvocationHandler {

    private T target;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public ServiceProxy(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(this.target);
        RemoteClass remoteClass = method.getDeclaringClass().getAnnotation(RemoteClass.class);

        if (null == remoteClass) {
            throw new Exception("远程类型未指定");
        }

        List<String> argTypeList = new ArrayList<>();
        if (args != null) {
            for (Object obj : args) {
                argTypeList.add(obj.getClass().getName());
            }
        }

        String argTypes = objectMapper.writeValueAsString(argTypeList);
        String argValues = objectMapper.writeValueAsString(args);

        // Result result = HttpUtil.callRemoteService(remoteClass.value(), method.getName(), argTypes, argValues);
        Result result = ThrifCallUtil.callRemoteService(remoteClass.value(), method.getName(), argTypes, argValues);

        if (result.isSuccess()) {
            System.out.println(result.getResultValue());
            // return objectMapper.readValue(result.getResultValue(), Class.forName(result.getResultType()));
            return result.getResultValue();
        } else {
            throw new Exception("远程调用异常：" + result.getMessage());

        }
    }
    
}