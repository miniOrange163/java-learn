package com.lin.redis.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sujl on 2017/2/26.
 */
@Service
public class RedisList {
    private static final Logger logger = LoggerFactory.getLogger(RedisMap.class);
    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;
    public String fixedLengthKey = "RedisFixedLength";
    private final String prefix = "LIST:";
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
    public void setFixedLength(String table,long len){
        redisTemplate.opsForHash().put (fixedLengthKey, table, len);
    }
    public long getFixedLength(String table){
        Object o = null;
        try {
            o = redisTemplate.opsForHash().get(fixedLengthKey, table);
            return (long) o;
        } catch ( Exception ex){
            logger.error(ex.getMessage());
        }
        System.out.println(String.valueOf(o));
        return 0;
    }
    /**
     * 读取整个内存表
     * @param table
     * @return
     */
    public <T> List<T> getTable(String table) {
        try {
            return (List<T>)redisTemplate.opsForList().range(prefix+table,0,-1);
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return null;
    }
    public <T> T getAndDelete(String table) {
        return (T)redisTemplate.opsForList().leftPop(prefix+table);
    }

    public <T> List<T> getListAndDelete(String table, long n) {
        try {
            List<T> retObj = new ArrayList<T>();
            for (int i=0;i<n;i++){
                T obj = (T)redisTemplate.opsForList().leftPop(prefix+table);
                if(obj==null){
                    break;
                }
                retObj.add(obj);
            }
            return retObj;
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return null;
    }
    /**
     * 插入一条内存表数据尾部
     * @param table
     * @param object
     * @return
     */
    public <T> Long rightPush(String table, T object){
        try {
            return redisTemplate.opsForList().rightPush(prefix+table,object);
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return 0L;
    }
    /**
     * 删除尾部一条数据
     * @param table
     * @return
     */
    public <T> T rightPop(String table){
        try {

            Object o = redisTemplate.opsForList().rightPop(prefix+table);

            return (T) o;
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return null;
    }
    /**
     * 插入一条内存表数据尾部
     * @param table
     * @param object
     * @return
     */
    public <T> Long push(String table, T object){
        try {
            return redisTemplate.opsForList().rightPush(prefix+table,object);
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return 0L;
    }
    /**
     * 删除尾部一条数据
     * @param table
     * @return
     */
    public boolean pop(String table){
        boolean result = false;
        try {
            Object o = redisTemplate.opsForList().rightPop(table);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return result;
    }
    public <T> Long pushAll(String table, List<T> objectList){
        long num = 0L;
        try {
            for (T object : objectList) {
                redisTemplate.opsForList().rightPush(prefix + table, object);
                num++;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return num;
    }
    public <T> Long rightPushAll(String table, List<T> objectList){
        long num = 0L;
        try {
            num = redisTemplate.opsForList().rightPushAll(table, objectList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }

        return num;
    }
    /**
     * 插入一条内存表数据尾部(定长队列)
     * @param table
     * @param object
     * @return
     */
    public  <T> Long fixedPush(String table, T object){
        try {
            long fixedLength = getFixedLength(table);
            if(fixedLength > 0) {
                return fixedPush(table, object,fixedLength);
            } else {
                logger.error("未设置队列长度");
                return 0L;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return 0L;
    }
    /**
     * 插入一条内存表数据尾部(定长队列)
     * @param table
     * @param object
     * @return
     */
    public  <T> Long fixedPush(String table, T object,long fixedLength){
        Long ret = redisTemplate.opsForList().rightPush(prefix+table, object);
        Long nowLength = size(table);
        if(nowLength > fixedLength) {
            redisTemplate.opsForList().trim(prefix+table, nowLength-fixedLength,-1);
        }
        return ret;
    }

    /**
     * 删除整个内存表
     * @param table
     * @return
     */
    public boolean deleteTable(String table){
        boolean result = false;
        try {
            redisTemplate.delete(prefix+table);
            result = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return result;
    }
    public Long size(String table){
        return  redisTemplate.opsForList().size(prefix+table);
    }
    /**
     * 获取所有缓存key
     * @param pattern 匹配参数("*"返回所有key)
     * @return
     */
    public Set<Serializable> tables(String pattern){
        return (Set<Serializable>)redisTemplate.keys(prefix+pattern);
    }
}
