package com.design.eventdriven.event;

import com.design.eventdriven.framework.HanderEnum;
import com.design.eventdriven.model.User;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/24
 *  
 * @name: 
 *
 * @Description: 
 */
public class UserUpdateEvent extends AbstractEvent {

    private User user;

    private HanderEnum handerEnum = HanderEnum.update;
    public UserUpdateEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public HanderEnum getEnum() {
        return handerEnum;
    }
}
