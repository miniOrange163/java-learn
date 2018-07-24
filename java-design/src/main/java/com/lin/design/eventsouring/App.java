package com.lin.design.eventsouring;

import com.lin.design.eventsouring.event.AccountCreateEvent;
import com.lin.design.eventsouring.event.AccountDepositEvent;
import com.lin.design.eventsouring.event.AccountTransferEvent;
import com.lin.design.eventsouring.processor.DomainEventProcessor;



import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 事件捕捉
 *
 * @Description: 1.开户、存钱、转账每个都是一个独立的事件
 *               2.DomainEventProcessor事件处理类负责对具体的事件进行处理,起到了系统运行的枢纽作用
 *               3.Account账户实体类被隐藏了起来，外部不会观察到具体的账户类
 *               4.每个事件都会被转为json字符串记录起来，当需要回顾时，可以从文件中读取事件列表，按发生顺序依次回顾
 */
public class App {

    public static final int ACCOUNT_OF_GUOJING = 1;

    public static final int ACCOUNT_OF_HUANGRONG = 2;


    public static void main(String[] args) {
        DomainEventProcessor processor = new DomainEventProcessor();


        processor.reset();
        //开户
        processor.process(new AccountCreateEvent(0,new Date().getTime(),ACCOUNT_OF_GUOJING,"郭靖"));
        processor.process(new AccountCreateEvent(1,new Date().getTime(),ACCOUNT_OF_HUANGRONG,"黄蓉"));
        //存钱
        processor.process(new AccountDepositEvent(3,new Date().getTime(),ACCOUNT_OF_GUOJING,new BigDecimal(1000)));
        processor.process(new AccountDepositEvent(4,new Date().getTime(),ACCOUNT_OF_HUANGRONG,new BigDecimal(500)));
        //转账
        processor.process(new AccountTransferEvent(5,new Date().getTime(),ACCOUNT_OF_GUOJING,ACCOUNT_OF_HUANGRONG,new BigDecimal(500)));

        processor.recover();

    }



}
