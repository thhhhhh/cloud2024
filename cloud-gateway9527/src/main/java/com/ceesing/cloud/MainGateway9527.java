package com.ceesing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //服务注册和发现
public class MainGateway9527
{
    public static void main(String[] args)
    {
        SpringApplication.run(MainGateway9527.class,args);
    }
}