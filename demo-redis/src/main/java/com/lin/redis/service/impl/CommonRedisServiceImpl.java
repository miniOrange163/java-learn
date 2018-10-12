package com.lin.redis.service.impl;


import com.lin.redis.service.CommonRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2017/3/30.
 *
 * @param <T> 泛型
 */
@Service
public class CommonRedisServiceImpl<T> implements CommonRedisService<T> {

    private static final Logger logger = LoggerFactory.getLogger(CommonRedisServiceImpl.class);
    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys 键列表
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern 模式
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0){
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key 键
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key 键
     * @return 是否成功
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate
                .opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key 键
     * @param value 值
     * @return 是否成功
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(),e);
        }
        return result;
    }


    @Override
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(),e);
        }
        return result;
    }


    /**
     * 获取所有缓存key
     * @param pattern 匹配参数("*"返回所有key)
     * @return 列表
     */
    public Set<Serializable> keys(String pattern){
        return (Set<Serializable>)redisTemplate.keys(pattern);
    }
    /**
     * 更新缓存时间
     *
     * @param key 键
     * @param expireTime 时间长度
     * @return 是否成功
     */
    public boolean expire(final String key, Long expireTime) {

        boolean result = false;
        try {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(),e);
        }
        return result;
    }


    /**
     *
     * @param redisTemplate  设置模版
     */
    public void setRedisTemplate(
            RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean batchSetByMap(String tableKey, Map<String,?> cacheMap){
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(tableKey, cacheMap);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(),e);
        }
        return result;
    }

    /**
     *
     * 跟据mapKey获取对应数据
     * @param tableKey 表
     * @param mapKey 集合
     * @return 值
     */
    public Object getMapByKey(String tableKey,String mapKey){
        return  redisTemplate.opsForHash().get(tableKey, mapKey);
    }
    /**
     *
     * 跟据mapKey获取对应数据
     * @param tableKey 表
     * @return 值
     */
    public Object getMapAll(String tableKey){
        return  redisTemplate.opsForHash().entries(tableKey);
    }

    public boolean setByKey(String table, String key, T object){
        boolean result = false;
        try{
            redisTemplate.opsForHash().put(table, key, object);
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return result;
    }

    /**
     * 从内存表读取一条数据,失败返回null
     * @param table 表
     * @param key 键
     * @return 值
     */
    public T getByKey(String table, String key){
        try {
            return (T) redisTemplate.opsForHash().get(table, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return null;
    }


    /**
     * 将表中某个键数据自增1
     * @param table 表
     * @param key 键
     * @return 值
     */
    public boolean increment(String table,String key){
        boolean result = false;
        try {
                redisTemplate.opsForHash().increment(table,key,1);

            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }

    /**
     * 获取内存表所有键值
     * @param table 表
     * @return 集合
     */
    public Set<T> getTableKeys(String table){
        try {
            Set<T> keys = (Set<T>) redisTemplate.opsForHash().keys(table);
            return keys;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return null;
    }

    /**
     * 获取hashMap中某个二维字段的自增量
     * @param table 表
     * @param key 键
     * @return 值
     */
    public long getHashIncByKey(String table,String key){
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    RedisSerializer<String> serialString=redisTemplate.getStringSerializer();
                    RedisSerializer<String> serialHashKey= (RedisSerializer<String>) redisTemplate.getHashKeySerializer();
                    byte[] tableKey=serialString.serialize(table);
                    byte[] hasKey = serialHashKey.serialize(key);
                    byte[] value = connection.hGet(tableKey,hasKey);
                    String val=serialString.deserialize(value);
                    return Long.parseLong(val);
                    //return byteToLong(value);
                }  catch (Exception e) {
                    return 0L;
                }
            }
        });
    }

    /**
     * 删除整个内存表
     * @param table 表
     * @return 是否成功
     */
    public boolean deleteTable(String table){
        boolean result = false;
        try {
            redisTemplate.delete(table);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }

    /**
     * 根据键值删除内存表数据
     * @param table 表
     * @param key 键
     * @return 是否成功
     */
    public boolean deleteByKey(String table, String key) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().delete(table, key);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }
    /**
     * 根据键值删除数据
     * @param key 键
     * @return 值
     */
    public boolean deleteByKey(String key) {
        boolean result = false;
        try {
//            redisTemplate.opsForHash().delete(key);
            redisTemplate.delete(key);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }
}
