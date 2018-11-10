package com.huadong.spring.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义一个 Ribbon的负载均衡策略 算法
 * 这个配置要放在主程序扫描不到的包中，否则这个配置将被所有的RibbonClient共用；
 */
@Configuration
public class RibbonAlg {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
