package com.IT.service.edu.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/2/12 14:59
 */

@Configuration
public class OpenFeignConfig {


    @Bean
    Logger.Level feignLoggerLevel(){
//NONE(默认级别，不显示日志),BASIC(仅记录请求方法、URL、响应状态及执行时间)
// ,HEADERS(除了BASIC中定义的信息之外，还有请求和响应头信息),FULL(除了HEADERS中定义的信息之外，还有请求和响应正文及元数据信息);
        return Logger.Level.FULL;
    }
}
