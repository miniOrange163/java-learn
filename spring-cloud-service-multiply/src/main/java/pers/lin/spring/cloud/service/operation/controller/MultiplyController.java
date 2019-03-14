package pers.lin.spring.cloud.service.operation.controller;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.lin.spring.cloud.service.operation.entity.OperEntity;
import pers.lin.spring.cloud.service.operation.entity.ResultEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/1/16
 *  
 * @name: 
 *
 * @Description: 
 */
@RestController
@RequestMapping("oper/")
public class MultiplyController {


    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String name;
    public static Environment myEnvironment ;

    @Autowired
    void setEnviroment(Environment env) {
        myEnvironment = env;
    }

    @RequestMapping(value = "getConfigValue")
    public String getConfigValue(HttpServletRequest request) {
        String config = request.getParameter("config");
        String property = myEnvironment.getProperty(config);
        return property;
    }


    @RequestMapping(value = "name")
    public String name(HttpServletRequest request) {
        return name + "-" + port;
    }

    @RequestMapping(value = "port")
    public int port(HttpServletRequest request) {

        return Integer.parseInt(port);

    }

    @RequestMapping(value = "multiply")
    public ResultEntity multiply(@RequestBody OperEntity entity) {


        Double a = Double.parseDouble(entity.getA());

        Double b = Double.parseDouble(entity.getB());

        ResultEntity result = new ResultEntity();
        result.setResult(a*b);
        result.setPort(Integer.parseInt(port));
        result.setName(name+ "-" + port);


        return result;

    }
}
