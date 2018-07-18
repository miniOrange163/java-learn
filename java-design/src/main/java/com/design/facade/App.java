package com.design.facade;
/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/28
 *  
 * @name: 外观模式
 *
 * @Description: 1.为子系统中的一组接口提供统一的接口
 *               2.SoftwareDepartmentFacade作为一个统一的外观
 *               3.SoftwareDepartmentFacade内部有多个继承了Worker抽象类的不同实现类
 */
public class App {

    public static void main(String[] args) {
        SoftwareDepartmentFacade soft = new SoftwareDepartmentFacade();
        soft.goToWork();
        soft.development();
        soft.offWork();
    }

}
