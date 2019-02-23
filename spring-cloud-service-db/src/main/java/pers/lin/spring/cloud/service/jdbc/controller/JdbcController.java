package pers.lin.spring.cloud.service.jdbc.controller;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.lin.spring.cloud.service.jdbc.entity.RoleEntity;
import pers.lin.spring.cloud.service.jdbc.service.MysqlService;

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
@RequestMapping("db/")
@RefreshScope
public class JdbcController {

    @Autowired
    void setEnviroment(Environment env) {
        myEnvironment = env;
    }


    public static Environment myEnvironment ;
    @Value("${config.value}")
    private String configValue;

    @Autowired
    private MysqlService mysqlService;

    @RequestMapping(value = "getConfigValue")
    public String getConfigValue(HttpServletRequest request) {

        String property = myEnvironment.getProperty("config.value");
        return property;
//        return configValue;
    }

    @RequestMapping(value = "mysql")
    public RoleEntity getValueFromMysql(HttpServletRequest request) {

        String id = request.getParameter("id");
        RoleEntity roleEntity = mysqlService.selectRole(id);

        return roleEntity;

    }
    @RequestMapping(value = "redis")
    public String getValueFromRedis(HttpServletRequest request) {

        return null;

    }

}
