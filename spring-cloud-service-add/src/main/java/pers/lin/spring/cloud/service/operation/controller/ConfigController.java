package pers.lin.spring.cloud.service.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lin.spring.cloud.service.operation.entity.OperEntity;
import pers.lin.spring.cloud.service.operation.entity.ResultEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

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
@RequestMapping("config/")
@RefreshScope
public class ConfigController {

    @Autowired
    void setEnviroment(Environment env) {
        myEnvironment = env;
    }

    @Autowired
    private ContextRefresher contextRefresher;

    public static Environment myEnvironment ;
    @Value("${config.value}")
    private String configValue;


    @RequestMapping(value = "getConfigValue")
    public String getConfigValue(HttpServletRequest request) {

//        String property = myEnvironment.getProperty("config.value");
//        return property;
        return configValue;
    }
    // 主动刷新配置
    // 此方法，测试未成功，调用后长时间无返回，待确认用法是否正确
    @RequestMapping(value = "refresh")
    public String refresh(HttpServletRequest request) {

        Set<String> refresh = contextRefresher.refresh();
        return refresh.toString();
    }
}
