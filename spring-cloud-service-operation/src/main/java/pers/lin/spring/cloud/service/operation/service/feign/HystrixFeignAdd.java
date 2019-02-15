package pers.lin.spring.cloud.service.operation.service.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pers.lin.spring.cloud.service.operation.entity.OperEntity;
import pers.lin.spring.cloud.service.operation.entity.ResultEntity;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/2/12
 *  
 * @name: 
 *
 * @Description: 
 */
@Component
public class HystrixFeignAdd implements FeignAddService{

    private Logger logger = LoggerFactory.getLogger(HystrixFeignAdd.class);

    @Override
    public ResultEntity add(OperEntity entity) {
        logger.error("service-operation-add" + "/oper/add" + "-不可用。a:{},b{}",entity.getA(),entity.getB());
        return null;
    }

    @Override
    public int port() {
        return 0;
    }
}