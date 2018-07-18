package com.design.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/28
 *  
 * @name: 后端工程师
 *
 * @Description: 
 */
public class BackWorker extends Worker {
    private static final Logger logger = LoggerFactory.getLogger(BackWorker.class);

    public BackWorker() {
        super("后端工程师");
    }


    @Override
    public void work() {
        logger.info("{} 开发后端接口",getProfession());
    }



}
