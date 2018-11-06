package com.huadong.spring.ribbon;

import com.huadong.spring.com.huadong.spring.constant.ServiceName;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 定义User 服务的Ribbon 配置,这个配置会覆盖默认的配置
 *
 * 也可以不通过定义RibbonAlg和UserRibbonClientConfig着中国形式，直接在配置文件添加
 * user.ribbon.NFLoadBalancerRuleClassName:com.netflix.loadbalancer.RandomRule
 *
 */
@Configuration
@RibbonClient(name = ServiceName.userService,configuration = RibbonAlg.class)
public class UserRibbonClientConfig {
}
