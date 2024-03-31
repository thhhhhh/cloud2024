package com.ceesing.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config
{
    // 分组配置，将所有的模块接口进行分组，方便前端按照功能模块查看接口
    @Bean
    public GroupedOpenApi PayApi()
    {
        return GroupedOpenApi.builder().group("支付微服务模块").pathsToMatch("/pay/**").build();
    }
    @Bean
    public GroupedOpenApi OtherApi()
    {
        return GroupedOpenApi.builder().group("其它微服务模块").pathsToMatch("/other/**", "/others").build();
    }
    /*@Bean
    public GroupedOpenApi CustomerApi()
    {
        return GroupedOpenApi.builder().group("客户微服务模块").pathsToMatch("/customer/**", "/customers").build();
    }*/

    // 对 swagger3 页面内容进行描述
    @Bean
    public OpenAPI docsOpenApi()
    {
        return new OpenAPI()
                // 项目信息描述
                .info(new Info().title("cloud2024")
                        .description("通用设计rest")
                        .version("v1.0"))
                // 有问题如何联系
                .externalDocs(new ExternalDocumentation()
                        .description("www.ceesing.com")
                        .url("https://yiyan.baidu.com/"));
    }
}