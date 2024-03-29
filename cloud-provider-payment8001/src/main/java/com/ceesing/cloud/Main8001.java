package com.ceesing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.ceesing.cloud.mapper") //避免在每一个mapper类上添加注解 @mapper
public class Main8001
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main8001.class,args);
    }
}