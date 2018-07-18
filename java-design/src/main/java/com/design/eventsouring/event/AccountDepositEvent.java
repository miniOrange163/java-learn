package com.design.eventsouring.event;

import com.design.eventsouring.domain.Account;
import com.design.eventsouring.state.AccountAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 存款事件
 *
 * @Description: 
 */
public class AccountDepositEvent extends DomainEvent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDepositEvent.class);
    private final int id;
    private final BigDecimal money;

    public AccountDepositEvent(long sequenceId, long createdTime,int id, BigDecimal money) {
        super(sequenceId, createdTime, AccountDepositEvent.class.getSimpleName());
        this.id = id;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    @Override
    public void process() {

        Account account = AccountAggregate.getInstance().get(id);
        account.handleEvent(this);
        LOGGER.info("deposit :" + account.getName());


    }
}
