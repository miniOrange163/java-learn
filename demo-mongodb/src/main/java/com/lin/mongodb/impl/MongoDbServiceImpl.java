package com.lin.mongodb.impl;

import com.lin.mongodb.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.xml.ws.Action;

/**
 * @author: Linjb
 * @Date: 2018/10/10
 * @name:
 * @Description:
 */
public class MongoDbServiceImpl implements MongoDbService {

    @Autowired
    private MongoTemplate mongoTemplate;


    private void test(){

    }
}
