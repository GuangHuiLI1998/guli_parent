package com.IT.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/1/12 23:04
 */

@SpringBootApplication
@ComponentScan({"com.IT.service"})
@EnableDiscoveryClient //  注册中心
@EnableFeignClients //OpenFeign是Spring Cloud提供的一个声明式的伪Http客户端
public class ServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class,args);
    }
}
