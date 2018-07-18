package com.design.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/28
 *  
 * @name: 
 *
 * @Description: 
 */
public abstract class Worker {

    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    private String profession;


    public Worker(String profession) {
        this.profession = profession;
    }


    public static enum Action{
        GO_TO_WORK,WORK,OFF_WORK
    }
    public void goToWork(){
        logger.info("{} 上班了", getProfession());
    }

    public void offWork(){
        logger.info("{} 下班了", getProfession());
    }

    public void action(Action action) {
        switch (action) {
            case GO_TO_WORK:goToWork();break;
            case WORK:work();break;
            case OFF_WORK:offWork();break;
            default:logger.info("无动作");break;

        }
    }

    public void action(Action... actions) {
        for (Action action : actions) {
            action(action);
        }
    }



    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }


    public abstract void work();


}
