package pers.lin.spring.cloud.service.operation.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.lin.spring.cloud.service.operation.entity.OperEntity;
import pers.lin.spring.cloud.service.operation.entity.ResultEntity;

/**
 * @author: 林嘉宝
 * @Date: 2019/2/11
 * @name:
 * @Description:
 */
@FeignClient(value = "service-operation-divide")
public interface FeignDivideService {

    @RequestMapping(value = "/oper/divide")
    ResultEntity divide(@RequestBody OperEntity entity);

    @RequestMapping(value = "/oper/port")
    int port();
}
