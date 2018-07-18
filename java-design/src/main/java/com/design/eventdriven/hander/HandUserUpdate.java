package com.design.eventdriven.hander;

import com.design.eventdriven.event.UserAddEvent;
import com.design.eventdriven.event.UserUpdateEvent;
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
public class HandUserUpdate implements Hander<UserUpdateEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandUserUpdate.class);
    @Override
    public void onEvent(UserUpdateEvent userAddEvent) {
        LOGGER.info("update : " + userAddEvent.getUser().getName());
    }
}
