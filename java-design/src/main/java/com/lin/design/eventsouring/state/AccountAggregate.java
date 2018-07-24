package com.lin.design.eventsouring.state;

import com.lin.design.eventsouring.domain.Account;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 
 *
 * @Description: 
 */
public class AccountAggregate {



    private static Map<Integer, Account> map = new ConcurrentHashMap<>();

    private volatile static AccountAggregate aggregate;

    private static Object LOCK = new Object();

    private AccountAggregate(){

    }
    public static AccountAggregate getInstance(){
        if(aggregate == null){
            synchronized (LOCK) {
                if(aggregate == null){
                    aggregate = new AccountAggregate();
                }
            }
        }
        return aggregate;
    }

    public void put(Account account) {
        map.put(account.getId(), account);
    }

    public Account get(Integer id) {
        Account account = map.get(id);
        if (account == null) {
            return null;
        }
        return account.copy();
    }


}
