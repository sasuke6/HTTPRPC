package com.hsucy.client.remoteservice;

import com.hsucy.client.rpc.RemoteClass;

@RemoteClass("com.hsucy.server.service.UserService")
public interface UserService {
    Integer getUserCount();
}