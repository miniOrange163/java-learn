package com.lin.design.eventsouring.processor;

import com.lin.design.eventsouring.event.DomainEvent;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 
 *
 * @Description: 
 */
public class DomainEventProcessor {

    private JsonFileJournal json = new JsonFileJournal();

    public void process(DomainEvent event){

        event.process();
        json.write(event);

    }
    public void recover(){
        DomainEvent event;
        while ((event = json.readNext()) != null) {
            event.process();
        }
    }
    public void reset(){
        json.reset();
    }
}
