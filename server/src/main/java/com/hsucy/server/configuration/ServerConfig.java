package com.hsucy.server.configuration;


import com.hsucy.server.service.UserServiceImpl;
import com.hsucy.server.service.UserServiceTh;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public void startServer() {
        logger.info("thrift服务启动");
        UserServiceTh.Processor<?> processor = new UserServiceTh.Processor<UserServiceTh.Iface>(new UserServiceImpl());
        try {
            TServerTransport transport = new TServerSocket(12311);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
            tArgs.processor(processor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TTransportFactory());
            tArgs.minWorkerThreads(10);
            tArgs.maxWorkerThreads(20);
            TServer server = new TThreadPoolServer(tArgs);
            server.serve();
        } catch (Exception e) {
            logger.error("thrift服务启动失败", e);
        }
    }
    
}