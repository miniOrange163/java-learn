package pers.lin.spring.cloud.service.operation.service.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pers.lin.spring.cloud.service.operation.entity.OperEntity;
import pers.lin.spring.cloud.service.operation.entity.PortInfoEntity;
import pers.lin.spring.cloud.service.operation.entity.ResultEntity;
import pers.lin.spring.cloud.service.operation.service.OperationService;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/1/21
 *  
 * @name: 
 *
 * @Description: 
 */
@Service("ribbonService")
public class OperationRibbonServiceImpl implements OperationService {

    private Logger logger = LoggerFactory.getLogger(OperationRibbonServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    private final static String SERVICE_ADD ="service-operation-add";
    private final static String SERVICE_SUBTRACT ="service-operation-substract";
    private final static String SERVICE_MULTIPLY ="service-operation-multiply";
    private final static String SERVICE_DIVIDE ="service-operation-divide";

    @Override
    @HystrixCommand(fallbackMethod = "addError")
    public ResultEntity add(String a, String b) {
        OperEntity oper = new OperEntity();
        oper.setA(a);
        oper.setB(b);

        String response = restTemplate.postForObject("http://" + SERVICE_ADD + "/oper/add", oper, String.class);

        ResultEntity result = JSON.parseObject(response,ResultEntity.class);
        return result;

    }

    public ResultEntity addError(String a, String b){
        logger.error(SERVICE_ADD + "/oper/add" + "-不可用。a:{},b{}",a,b);
        return null;
    }
    @Override
    public ResultEntity substract(String a, String b) {

        OperEntity oper = new OperEntity(a,b);
        String response = restTemplate.postForObject("http://" + SERVICE_SUBTRACT + "/oper/substract", oper, String.class);
        return JSON.parseObject(response,ResultEntity.class);
    }

    @Override
    public ResultEntity multiply(String a, String b) {
        OperEntity oper = new OperEntity(a,b);
        String response = restTemplate.postForObject("http://" + SERVICE_MULTIPLY + "/oper/multiply", oper, String.class);
        return JSON.parseObject(response,ResultEntity.class);
    }

    @Override
    public ResultEntity divide(String a, String b) {
        OperEntity oper = new OperEntity(a,b);
        String response = restTemplate.postForObject("http://" + SERVICE_DIVIDE + "/oper/divide", oper, String.class);
        return JSON.parseObject(response,ResultEntity.class);
    }

    @Override
    public PortInfoEntity port() {

        String add = restTemplate.postForObject("http://" + SERVICE_ADD + "/oper/port", null, String.class);
        String substract = restTemplate.postForObject("http://" + SERVICE_SUBTRACT + "/oper/port", null, String.class);
        String multiply = restTemplate.postForObject("http://" + SERVICE_MULTIPLY + "/oper/port", null, String.class);
        String divide = restTemplate.postForObject("http://" + SERVICE_DIVIDE + "/oper/port", null, String.class);

        PortInfoEntity port = new PortInfoEntity();
        port.setAdd(Integer.parseInt(add));
        port.setSubtract(Integer.parseInt(substract));
        port.setMultiply(Integer.parseInt(multiply));
        port.setDivide(Integer.parseInt(divide));
        return port;
    }


}
