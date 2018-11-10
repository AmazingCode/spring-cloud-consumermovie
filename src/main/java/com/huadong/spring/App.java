package com.huadong.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 */
@EnableEurekaClient // 注解支持
@SpringBootApplication
@EnableFeignClients
//@ComponentScan(,useDefaultFilters = false,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {FeignClientConfig.class, RibbonAlg.class}))
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("Hello World!");
    }

    /**
     * 定义RestTemplate Bean；并且使用客户端复杂均衡策略
     * <p>
     * （Ribbon负载均衡策略可以脱离Eureka独立使用，只要在配置文件中添加配置
     * {serverName}.ribbon.listOfServers=localhost:8081,localhost:8082即可
     * ）
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
