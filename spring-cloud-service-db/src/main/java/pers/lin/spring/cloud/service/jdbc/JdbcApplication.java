package pers.lin.spring.cloud.service.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("pers.lin.spring.cloud.service.jdbc.dao")
public class JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("config.value: "
                + env.getProperty("config.value"));
    }


}

