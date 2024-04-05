package com.ceesing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainNacosConfigClient3377
{
    public static void main(String[] args)
    {
        SpringApplication.run(MainNacosConfigClient3377.class,args);
    }
}