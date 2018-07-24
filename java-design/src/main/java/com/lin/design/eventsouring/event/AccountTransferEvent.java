package com.lin.design.eventsouring.event;

import com.lin.design.eventsouring.domain.Account;
import com.lin.design.eventsouring.state.AccountAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 转账事件
 *
 * @Description: 
 */
public class AccountTransferEvent extends DomainEvent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTransferEvent.class);

    private final BigDecimal money;
    private final int accountNoFrom;
    private final int accountNoTo;

    public AccountTransferEvent(long sequenceId, long createdTime, int accountNoFrom, int accountNoTo,BigDecimal money) {
        super(sequenceId, createdTime, AccountTransferEvent.class.getSimpleName());
        this.money = money;
        this.accountNoFrom = accountNoFrom;
        this.accountNoTo = accountNoTo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public int getAccountNoFrom() {
        return accountNoFrom;
    }

    public int getAccountNoTo() {
        return accountNoTo;
    }

    @Override
    public void process() {


        Account fromAccount = AccountAggregate.getInstance().get(accountNoFrom);
        if (fromAccount == null) {
            throw new RuntimeException("not fount Account,id:" + accountNoFrom);
        }
        fromAccount.handleEventFromEvent(this);
        Account toAccount = AccountAggregate.getInstance().get(accountNoTo);
        if (fromAccount == null) {
            throw new RuntimeException("not fount Account,id:" + accountNoTo);
        }
        toAccount.handleEventToEvent(this);
        LOGGER.info("acount transfer,from:"+fromAccount.getName() + ",to:"+toAccount.getName());
    }
}
