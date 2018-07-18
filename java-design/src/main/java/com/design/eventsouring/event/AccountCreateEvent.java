package com.design.eventsouring.event;

import com.design.eventsouring.domain.Account;
import com.design.eventsouring.state.AccountAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 开户事件
 *
 * @Description: 
 */
public class AccountCreateEvent extends DomainEvent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountCreateEvent.class);

    private final int id;
    private final String name;

    public AccountCreateEvent(long sequenceId, long createdTime, int id, String name) {
        super(sequenceId, createdTime, AccountCreateEvent.class.getSimpleName());
        this.id = id;
        this.name = name;
    }

    @Override
    public void process() {

        LOGGER.info("create acount:" + name);
        Account account = new Account(id,name);
        AccountAggregate.getInstance().put(account);
    }
}
