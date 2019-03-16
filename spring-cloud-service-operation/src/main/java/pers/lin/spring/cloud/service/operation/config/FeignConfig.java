package pers.lin.spring.cloud.service.operation.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/3/15
 *  
 * @name: 
 *
 * @Description: 
 */
@Configuration
public class FeignConfig {
    @Bean
    public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("max", "max");//添加认证的用户名密码
    }

}
