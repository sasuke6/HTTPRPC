package com.hsucy.server.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.thrift.TException;

public class UserServiceImpl implements UserServiceTh.Iface {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getId(int id) throws TException {

        logger.info("received getId, id = {}:", id);

        return "Yes, We can";
    }
    
}