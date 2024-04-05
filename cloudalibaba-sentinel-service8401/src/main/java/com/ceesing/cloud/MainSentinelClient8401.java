package com.ceesing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainSentinelClient8401
{
    public static void main(String[] args)
    {
        SpringApplication.run(MainSentinelClient8401.class,args);
    }
}