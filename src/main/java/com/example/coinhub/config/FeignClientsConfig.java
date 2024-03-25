package com.example.coinhub.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.coinhub.feign")
public class FeignClientsConfig {

}
