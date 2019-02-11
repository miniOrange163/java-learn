package com.lin.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    private static RedisTemplate redisTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-content.xml");

        redisTemplate = (RedisTemplate) context.getBean("redisTemplate");

//        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.delete("n");

        redisTemplate.opsForValue().increment("n", 1);
//        testString();

        testMap();

        testList();

        testSet();

        testSortSet();



    }

    public static void testString() {
        logger.info("----------------------测试字符串的用例----------------------");
        logger.info("设置键值");
        redisTemplate.opsForValue().set("key","value");

        String value = (String) redisTemplate.opsForValue().get("key");
        logger.info("获取键的值：" + value);

        logger.info("设置生命周期：100秒");
        redisTemplate.expire("key", 100, TimeUnit.SECONDS);


        Long life = redisTemplate.getExpire("key", TimeUnit.SECONDS);
        logger.info("键的生命周期：" + life + "秒");



        redisTemplate.opsForValue().set("key2","value2");
        redisTemplate.opsForValue().set("key3","value3");
        redisTemplate.opsForValue().set("key4","value4");
        redisTemplate.opsForValue().set("key5","value5");


        logger.info("按key*的模式获取多个键");
        Set keys = redisTemplate.keys("key*");

        for (Object key : keys) {

            Object val = redisTemplate.opsForValue().get(key);

            logger.info("键："+key+"，值："+val);
        }


    }

    private static void testMap() {

        logger.info("----------------------测试哈希的用例----------------------");
        logger.info("设置哈希");

        redisTemplate.opsForHash().put("map","map-key1",1);
        redisTemplate.opsForHash().put("map","map-key2",2);
        redisTemplate.opsForHash().put("map","map-key3",3);
        redisTemplate.opsForHash().put("map","map-key4",4);
        redisTemplate.opsForHash().put("map","map-key5",5);

        Set map = redisTemplate.opsForHash().keys("map");

        for (Object key : map) {

            Object val = redisTemplate.opsForHash().get("map", key);

            logger.info("键："+key+"，值："+val);

            redisTemplate.opsForHash().increment("map", key, 100);

        }

        for (Object key : map) {

            Object val = redisTemplate.opsForHash().get("map", key);

            logger.info("键："+key+"，值："+val);


        }

    }

    private static void testList() {

    }

    private static void testSet() {

    }

    private static void testSortSet() {


    }



}
