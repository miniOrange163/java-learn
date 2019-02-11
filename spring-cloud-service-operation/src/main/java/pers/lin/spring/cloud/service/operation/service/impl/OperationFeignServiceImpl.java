package pers.lin.spring.cloud.service.operation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lin.spring.cloud.service.operation.entity.OperEntity;
import pers.lin.spring.cloud.service.operation.entity.PortInfoEntity;
import pers.lin.spring.cloud.service.operation.entity.ResultEntity;
import pers.lin.spring.cloud.service.operation.service.OperationService;
import pers.lin.spring.cloud.service.operation.service.feign.FeignAddService;
import pers.lin.spring.cloud.service.operation.service.feign.FeignDivideService;
import pers.lin.spring.cloud.service.operation.service.feign.FeignMultiplyService;
import pers.lin.spring.cloud.service.operation.service.feign.FeignSubstractService;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/1/21
 *  
 * @name: 
 *
 * @Description: 
 */
@Service("feignService")
public class OperationFeignServiceImpl implements OperationService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FeignAddService addService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FeignSubstractService substractService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FeignMultiplyService multiplyService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FeignDivideService divideService;

    @Override
    public ResultEntity add(String a, String b) {

        OperEntity oper = new OperEntity(a,b);
        ResultEntity result = addService.add(oper);
        return result;

    }

    @Override
    public ResultEntity substract(String a, String b) {

        OperEntity oper = new OperEntity(a,b);
        ResultEntity result = substractService.substract(oper);
        return result;

    }

    @Override
    public ResultEntity multiply(String a, String b) {
        OperEntity oper = new OperEntity(a,b);
        ResultEntity result = multiplyService.multiply(oper);
        return result;

    }

    @Override
    public ResultEntity divide(String a, String b) {
        OperEntity oper = new OperEntity(a,b);
        ResultEntity result = divideService.divide(oper);
        return result;

    }

    @Override
    public PortInfoEntity port() {

        PortInfoEntity port = new PortInfoEntity();
        port.setAdd(addService.port());
        port.setSubtract(substractService.port());
        port.setMultiply(multiplyService.port());
        port.setDivide(divideService.port());
        return port;
    }


}
