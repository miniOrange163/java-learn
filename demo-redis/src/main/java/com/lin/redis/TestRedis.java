package com.lin.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/9/22
 *  
 * @name: 
 *
 * @Description: 
 */
public class TestRedis {

    private static Logger logger = LoggerFactory.getLogger(TestRedis.class);
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-content.xml");

//        RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");

        logger.info("abc");


//        System.out.println(redisTemplate.getClass());

    }

}
