package com.ceesing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//import tk.mybatis.spring.annotation.MapperScan;
@MapperScan("com.ceesing.cloud.mapper")     // 扫描mapper接口
@EnableDiscoveryClient                      //服务注册和发现
@EnableFeignClients                         // 开启feign
public class SeataAccountMainApp2002
{
    public static void main(String[] args)
    {
        SpringApplication.run(SeataAccountMainApp2002.class,args);
    }
}