package com.design.eventdriven.framework;

/**
 * @author: 林嘉宝
 * @Date: 2018/4/24
 * @name:
 * @Description:
 */
public interface Hander<E extends Event> {

    void onEvent(E e);

}
