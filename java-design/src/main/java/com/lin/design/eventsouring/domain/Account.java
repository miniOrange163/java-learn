package com.lin.design.eventsouring.domain;

import com.lin.design.eventsouring.event.AccountCreateEvent;
import com.lin.design.eventsouring.event.AccountDepositEvent;
import com.lin.design.eventsouring.event.AccountTransferEvent;
import com.lin.design.eventsouring.state.AccountAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 
 *
 * @Description: 
 */
public class Account {

    private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);

    private final int id;
    private final String name;

    private BigDecimal money;

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
        money = BigDecimal.ZERO;
    }

    public Account copy() {
        Account copy = new Account(id, name);
        copy.setMoney(this.money);

        return copy;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void addMoney(BigDecimal money) {
        this.money = this.money.add(money);
    }

    public void subMoney(BigDecimal money) {
        this.money = this.money.subtract(money);
    }

    private void handleAdd(BigDecimal money, boolean realTime) {

        addMoney(money);
        AccountAggregate.getInstance().put(this);
        if (realTime) {
            LOGGER.info("realTime add");
        }
    }

    private void handleSub(BigDecimal money, boolean realTime) {
        if (this.money.compareTo(money) == -1) {
            throw new RuntimeException("Insufficient Account Balance");
        }
        subMoney(money);
        AccountAggregate.getInstance().put(this);
        if (realTime) {
            LOGGER.info("realTime sub");
        }
    }

    public void handleEvent(AccountCreateEvent event){
        AccountAggregate.getInstance().put(this);
        if (event.isRealTime()) {
            LOGGER.info("Some external api for only realtime execution could be called here.");
        }
    }

    public void handleEvent(AccountDepositEvent event) {
        handleAdd(event.getMoney(),event.isRealTime());
    }
    public void handleEventFromEvent(AccountTransferEvent event){
        handleSub(event.getMoney(),event.isRealTime());
    }
    public void handleEventToEvent(AccountTransferEvent event){
        handleAdd(event.getMoney(),event.isRealTime());
    }
}
