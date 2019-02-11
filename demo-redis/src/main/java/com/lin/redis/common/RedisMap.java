package com.lin.redis.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by sujl on 2017/2/26.
 */
@Service
public class RedisMap {
    private final Logger logger = LoggerFactory.getLogger(RedisMap.class);
    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplate<Serializable, Object> getRedisTemplate() {
        return redisTemplate;
    }
    /**
     * 插入一条内存表数据
     * @param table
     * @param key
     * @param object
     * @return
     */
    public <T> boolean setByKey(String table, String key, T object){
        boolean result = false;
        try {
            redisTemplate.opsForHash().put (table, key, object);
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return result;
    }

    /**
     * 从内存表读取一条数据,失败返回null
     * @param table
     * @param key
     * @return
     */
    public <T> T getByKey(String table, String key){
        try {
            return (T) redisTemplate.opsForHash().get(table, key);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
    /**
     * 判断内存表是否存在对应键值,失败返回null
     * @param table
     * @param key
     * @return
     */
    public Boolean hasKey(String table, String key){
        try {
            return redisTemplate.opsForHash().hasKey(table, key);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
    /**
     *  以某个字段值为键值循环保存数组
     * @param table
     * @param keyName
     * @param objectList
     * @return
     */
    public <T> boolean batchSetList(String table, List<T> objectList, String keyName){
        boolean result = false;
        try {
            HashMap<String, T> pojoMap = new HashMap<>();
            for(int i = 0;i < objectList.size(); i++) {
                T pojoObj = (T)objectList.get(i);
                String key = pojoObj.getClass().getMethod(keyName).invoke(pojoObj).toString();
                pojoMap.put(key, pojoObj);
            }
            batchSetMap(table, pojoMap);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }
    /**
     * 批量保存数据
     * @param table
     * @param objectMap
     * @return
     */
    public <T> boolean batchSetMap(String table, HashMap<String, T> objectMap){
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(table, objectMap);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }
    /**
     * 读取整个内存表
     * @param table
     * @return
     */
    public <T> T getTable(String table) {
        try {
            return (T) redisTemplate.opsForHash().entries(table);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
    public <T> T getT(String table) {
        try {
            return (T) redisTemplate.opsForHash().values(table);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    /**
     * 获取内存表所有键值
     * @param table
     * @return
     */
    public <T> Set<T> getTableKeys(String table){
        try {
            Set<T> keys = (Set<T>) redisTemplate.opsForHash().keys(table);
            return keys;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
    /**
     * 根据键值删除内存表数据
     * @param table
     * @param key
     * @return
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
     * 将表中某个键数据自增1
     * @param table
     * @param key
     * @return
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
     * 将表中某个键数据自增n
     * @param table
     * @param key
     * @param n
     * @return
     */
    public boolean increment(String table,String key,long n){
        boolean result = false;
        try {
            redisTemplate.opsForHash().increment(table,key,n);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }
    /**
     * 将表中某个键数据自增n
     * @param table
     * @param key
     * @param n
     * @return
     */
    public boolean increment(String table,String key,double n){
        boolean result = false;
        try {
            redisTemplate.opsForHash().increment(table,key,n);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }
    /**
     * 获取hashMap中某个二维字段的自增量
     * @param table
     * @param key
     * @return
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
     * 初始化hashMap中某个二维字段的自增量
     * @param table
     * @param key
     * @param n
     * @return
     */
    public <T> T setHashIncInit(String table, String key, T n){
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serialString=redisTemplate.getStringSerializer();
                RedisSerializer<String> serialHashKey= (RedisSerializer<String>) redisTemplate.getHashKeySerializer();
                byte[] tableKey=serialString.serialize(table);
                byte[] hasKey = serialHashKey.serialize(key);
                long delNum = connection.hDel(tableKey, hasKey);
                //return connection.hIncrBy(tableKey, hasKey, n);
                connection.hSet(tableKey, hasKey, serialString.serialize(String.valueOf(n)));
                return n;
            }
        });
    }

    /**
     * 批量初始化hashMap中某个表所有字段自增量
     * @param table
     * @param incMap
     * @return
     */
    public <T> Integer setHashIncInitTable(String table, Map<String, T> incMap){
        return redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serialString=redisTemplate.getStringSerializer();
                RedisSerializer<String> serialHashKey= (RedisSerializer<String>) redisTemplate.getHashKeySerializer();
                RedisSerializer<String> serialHashValue= (RedisSerializer<String>) redisTemplate.getHashValueSerializer();
                byte[] tableKey=serialString.serialize(table);

                for (Map.Entry<String, T> entry : incMap.entrySet()) {
                    String key = entry.getKey();
                    T value = entry.getValue();
                    byte[] hasKey = serialHashKey.serialize(key);
                    connection.hSet(tableKey, hasKey, serialString.serialize(String.valueOf(value)));
                }
                return incMap.size();
            }
        });

    }

    /**
     * 获取hashMap中某个表所有数据
     * @param table
     * @return
     */
    public Map<String,?> getHashIncTable(String table){
        return redisTemplate.execute(new RedisCallback<Map<String, ?>>() {
            @Override
            public Map<String, ?> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serialString=redisTemplate.getStringSerializer();
                RedisSerializer<String> serialHashKey= (RedisSerializer<String>) redisTemplate.getHashKeySerializer();
                byte[] tableKey=serialString.serialize(table);
                Map<String, Object> retMap = new HashMap<>();
                Map<byte[], byte[]> mapAll =  connection.hGetAll(tableKey);
                for (Map.Entry<byte[], byte[]> entry : mapAll.entrySet()) {
                    String hasKey = serialHashKey.deserialize(entry.getKey());
                    Object value = serialString.deserialize(entry.getValue());
                    retMap.put(hasKey,value);
                }
                return retMap;
            }
        });
    }
    /**
     * 删除整个内存表
     * @param table
     * @return
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
     * 某个内存表包含数据条数
     * @param table
     * @return
     */
    public long size(String table){
        return  redisTemplate.opsForHash().size(table);
    }
    /**
     * 更新缓存时间
     * @param key
     * @param expireTime
     * @return
     */
    public boolean expire(final String key, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
