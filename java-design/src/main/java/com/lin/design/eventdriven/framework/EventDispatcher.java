package com.lin.design.eventdriven.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/24
 *  
 * @name: 
 *
 * @Description: 
 */
public class EventDispatcher {

    private Map<Class<? extends Event>, Hander> map = new HashMap<>();
    private Map<HanderEnum, Hander> enumMap = new HashMap<>();

    public void onEvent(Event event){

        map.get(event.getType()).onEvent(event);
    }

    public void onEventByEnum(Event e){

        enumMap.get(e.getEnum()).onEvent(e);
    }

    public void addHander(Class<? extends Event> classType,Hander hander) {
        map.put(classType,hander);
    }
    public void addEnumHander(HanderEnum enumType,Hander hander) {
        enumMap.put(enumType,hander);
    }
}
