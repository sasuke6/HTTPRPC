package com.hsucy.client.configuration;

import com.hsucy.client.remoteservice.UserServiceTh;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.*;


public class ThriftClient {
    private UserServiceTh.Client client;
    private TBinaryProtocol protocol;
    private TSocket transport;
    private String host;
    private int port;

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public void init() {
        this.transport = new TSocket(host, port);
        this.protocol = new TBinaryProtocol(transport);
        this.client = new UserServiceTh.Client(protocol);
    }

    public UserServiceTh.Client getRPCThriftService() {
        return client;
    }

    public void open() throws TTransportException {
        System.out.println(transport);
        this.transport.open();
    }

    public void close() {
        this.transport.close();
    }
    
}