package com.lin.design.facade;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/28
 *  
 * @name: 软件部门
 *
 * @Description: 
 */
public class SoftwareDepartmentFacade {

    private List<Worker> workerList;

    SoftwareDepartmentFacade(){
        workerList = new ArrayList<>();
        workerList.add(new FrontWorker());
        workerList.add(new BackWorker());
    }

    public void goToWork(){

        action(Worker.Action.GO_TO_WORK);
    }

    public void offWork(){
        action(Worker.Action.OFF_WORK);
    }

    public void development(){
        action(Worker.Action.WORK);
    }

    private void action(Worker.Action...actions){
        for (Worker worker : workerList) {
            worker.action(actions);
        }
    }


}
