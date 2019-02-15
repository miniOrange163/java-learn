package pers.lin.spring.cloud.service.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class ServiceOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOperationApplication.class, args);
    }

}

