package pers.lin.spring.cloud.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
public class MultiplyOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiplyOperationApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("config.value: "
                + env.getProperty("config.value"));
        System.out.println("common.value: "
                + env.getProperty("common.value:"));
    }
    // 关闭登录校验
    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }

    }
}

