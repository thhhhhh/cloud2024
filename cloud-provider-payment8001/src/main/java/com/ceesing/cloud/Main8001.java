package com.ceesing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.ceesing.cloud.mapper") //避免在每一个mapper类上添加注解 @mapper
public class Main8001
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main8001.class,args);
    }
}