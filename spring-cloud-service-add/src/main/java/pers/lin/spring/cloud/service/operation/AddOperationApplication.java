package pers.lin.spring.cloud.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
public class AddOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddOperationApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("config.value: "
                + env.getProperty("config.value"));
    }


}

