package com.hsucy.client.remoteservice;

import com.hsucy.client.rpc.RemoteClass;

@RemoteClass("com.hsucy.server.service.UserServiceImpl")
public interface UserServiceInf {
    String getId();
}