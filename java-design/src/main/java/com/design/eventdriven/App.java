package com.design.eventdriven;

import com.design.eventdriven.event.UserAddEvent;
import com.design.eventdriven.event.UserUpdateEvent;
import com.design.eventdriven.framework.EventDispatcher;
import com.design.eventdriven.framework.Hander;
import com.design.eventdriven.framework.HanderEnum;
import com.design.eventdriven.hander.HandUserAdd;
import com.design.eventdriven.hander.HandUserUpdate;
import com.design.eventdriven.model.User;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/24
 *  
 * @name: 基于事件驱动的体系结构
 *
 * @Description: 1.EventDispatcher作为事件调度员的角色，内部存储了具体事件处理类
 *               2.HandUserUpdate只处理用户添加事件，HandUserUpdate只处理用户修改事件
 *               3.当EventDispatcher接收到一个事件时，就会根据预先定义好的标识(类名或枚举等)，决定用哪一个处理类去处理该事件
 */
public class App {

    public static void main(String[] args) {

        Hander addHander = new HandUserAdd();
        Hander upateHander = new HandUserUpdate();

        EventDispatcher dispatcher = new EventDispatcher();
        //通过类名来区分处理事件
        dispatcher.addHander(UserAddEvent.class,addHander);
        dispatcher.addHander(UserUpdateEvent.class,upateHander);
        dispatcher.onEvent(new UserAddEvent(new User("add")));
        dispatcher.onEvent(new UserUpdateEvent(new User("update")));


        //通过枚举来区分处理事件
        dispatcher.addEnumHander(HanderEnum.add,addHander);
        dispatcher.addEnumHander(HanderEnum.update,upateHander);
        dispatcher.onEventByEnum(new UserAddEvent(new User("add second")));
        dispatcher.onEventByEnum(new UserUpdateEvent(new User("update second")));

    }

}
