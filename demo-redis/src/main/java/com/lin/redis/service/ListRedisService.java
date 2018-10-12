package com.lin.redis.service;

/**
 * @author: 林嘉宝
 *
 * @Date: 2018/10/9
 *
 * @name:
 *
 * @Description:
 */
public interface ListRedisService {

    <T> Long lpush(String key, T value);

}
