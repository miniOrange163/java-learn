package com.lin.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 2017/3/30.
 *
 * @param <T> 泛型
 */
public interface CommonRedisService<T> {

    /**
     * 批量删除对应的value
     *
     * @param keys 键列表
     */
    void remove(String... keys) ;

    /**
     * 批量删除key
     *
     * @param pattern 模式
     */
    void removePattern(String pattern) ;

    /**
     * 删除对应的value
     *
     * @param key 键
     */
     void remove(String key) ;

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key 键
     * @return 是否存在
     */
     boolean exists(String key);

    /**
     * 读取缓存
     *
     * @param key 键
     * @return 值
     */
     Object get(String key) ;

    /**
     * 写入缓存
     *
     * @param key 键
     * @param value 值
     * @return 是否成功
     */
     boolean set(String key, Object value) ;

    /**
     * 写入缓存
     *
     * @param key 键
     * @param value 值
     * @param       expireTime 时间长度
     * @return 是否成功
     */
     boolean set(String key, Object value, Long expireTime) ;

    /**
     *
     * @param table 表名
     * @param key 键
     * @param object 值
     * @return 是否成功
     */
     boolean setByKey(String table, String key, T object);

    /**
     *
     * @param table 表名
     * @param key 键
     * @return 值
     */
     T getByKey(String table, String key);
    /**
     * 获取所有缓存key
     * @param pattern 匹配参数("*"返回所有key)
     * @return 缓存set
     */
     Set<Serializable> keys(String pattern);
    /**
     * 更新缓存时间
     *
     * @param key 键
     * @param expireTime 时间长度
     * @return 是否成功
     */
     boolean expire(String key, Long expireTime);

    /**
     *
     * @param redisTemplate  设置模版
     */
     void setRedisTemplate(
             RedisTemplate<Serializable, Object> redisTemplate) ;

    /**
     *
     * 批量缓存数据
     * @param tableKey 表
     * @param cacheMap 键值MAP
     * @return 是否成功
     */
     boolean batchSetByMap(String tableKey, Map<String, ?> cacheMap);

    /**
     *
     * 跟据mapKey获取对应数据
     * @param tableKey 表
     * @param mapKey 键
     * @return 值
     */
     Object getMapByKey(String tableKey, String mapKey);
    /**
     *
     * 跟据mapKey获取对应数据
     * @param tableKey 表
     * @return 所有
     */
     Object getMapAll(String tableKey);

    /**
     * 将表中某个键数据自增1
     * @param table 表
     * @param key 键
     * @return 值
     */
    boolean increment(String table, String key);

    /**
     * 获取内存表所有键值
     * @param table 表
     * @return 所有键值
     */
    Set<T> getTableKeys(String table);

    /**
     * 获取hashMap中某个二维字段的自增量
     * @param table 表
     * @param key 键
     * @return 自增量
     */
    long getHashIncByKey(String table, String key);

    /**
     * 删除整个内存表
     * @param table 表
     * @return 是否成功
     */
    boolean deleteTable(String table);

    /**
     * 根据键值删除内存表数据
     * @param table 表
     * @param key 键
     * @return 是否成功
     */
    boolean deleteByKey(String table, String key);
    /**
     * 根据键值删除内存
     * @param key 键
     * @return 值
     */
    boolean deleteByKey(String key);
}
