package com.lin.design.eventdriven.event;

import com.lin.design.eventdriven.framework.Event;
import com.lin.design.eventdriven.framework.HanderEnum;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/24
 *  
 * @name: 
 *
 * @Description: 
 */
public abstract class AbstractEvent implements Event {


    @Override
    public Class<? extends Event> getType() {
        return getClass();
    }
    @Override
    public abstract HanderEnum getEnum();

}
