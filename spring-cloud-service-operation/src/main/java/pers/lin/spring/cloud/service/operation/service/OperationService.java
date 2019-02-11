package pers.lin.spring.cloud.service.operation.service;

import pers.lin.spring.cloud.service.operation.entity.PortInfoEntity;
import pers.lin.spring.cloud.service.operation.entity.ResultEntity;

/**
 * @author: 林嘉宝
 * @Date: 2019/1/21
 * @name:
 * @Description:
 */
public interface OperationService {

    ResultEntity add(String a , String b);
    ResultEntity substract(String a , String b);
    ResultEntity multiply(String a , String b);
    ResultEntity divide(String a , String b);

    PortInfoEntity port();

}
