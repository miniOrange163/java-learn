package pers.lin.spring.cloud.service.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SubstractOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubstractOperationApplication.class, args);
    }

}

