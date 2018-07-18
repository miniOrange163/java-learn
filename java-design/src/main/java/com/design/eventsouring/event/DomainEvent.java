package com.design.eventsouring.event;

import java.io.Serializable;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 
 *
 * @Description: 
 */
public abstract class DomainEvent implements Serializable{

    private final long sequenceId;
    private final long createdTime;
    private final String eventClassName;
    private boolean realTime = true;

    public DomainEvent(long sequenceId, long createdTime, String eventClass) {
        this.sequenceId = sequenceId;
        this.createdTime = createdTime;
        this.eventClassName = eventClass;
    }

    public abstract void process();

    public long getSequenceId() {
        return sequenceId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public String getEventClassName() {
        return eventClassName;
    }

    public boolean isRealTime() {
        return realTime;
    }

    public void setRealTime(boolean realTime) {
        this.realTime = realTime;
    }
}
