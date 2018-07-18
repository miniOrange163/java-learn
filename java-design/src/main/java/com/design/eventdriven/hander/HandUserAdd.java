package com.design.eventdriven.hander;

import com.design.eventdriven.event.UserAddEvent;
import com.design.eventdriven.framework.Event;
import com.design.eventdriven.framework.Hander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/24
 *  
 * @name: 
 *
 * @Description: 
 */
public class HandUserAdd implements Hander<UserAddEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandUserAdd.class);

    @Override
    public void onEvent(UserAddEvent userAddEvent) {
        LOGGER.info("create : " + userAddEvent.getUser().getName());
    }
}
