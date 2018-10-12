package com.lin.redis.service.impl;

import com.lin.redis.service.ListRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/10/9
 *  
 * @name: 
 *
 * @Description: 
 */
public class ListRedisServiceImpl implements ListRedisService {

    private static Logger logger = LoggerFactory.getLogger(ListRedisServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public <T> Long lpush(String key, T value) {

        Long result = redisTemplate.opsForList().leftPush(key, value);

        return result;
    }
}
