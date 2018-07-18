import com.design.eventsouring.event.AccountCreateEvent;
import com.design.eventsouring.event.AccountDepositEvent;
import com.design.eventsouring.event.AccountTransferEvent;
import com.design.eventsouring.processor.DomainEventProcessor;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 
 *
 * @Description: 
 */
public class AccountTest {

    public static final int ACCOUNT_OF_GUOJING = 1;

    public static final int ACCOUNT_OF_HUANGRONG = 2;

    @Test
    public void recover(){

        DomainEventProcessor processor = new DomainEventProcessor();
        processor.recover();

    }

    @Test
    public void create(){
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



    }


}
