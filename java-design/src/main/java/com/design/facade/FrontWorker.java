package com.design.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/28
 *  
 * @name: 前端工程师
 *
 * @Description: 
 */
public class FrontWorker extends Worker{

    private static final Logger logger = LoggerFactory.getLogger(FrontWorker.class);

    public FrontWorker() {
        super("前端工程师");
    }


    @Override
    public void work() {

        logger.info("{} 开发前端界面",getProfession());

    }
}
