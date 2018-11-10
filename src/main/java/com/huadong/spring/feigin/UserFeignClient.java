package com.huadong.spring.feigin;

import com.huadong.spring.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 由于这里 使用了Eureka，name属性会被解析成 Eureka中的服务
 * <p>
 * 如果不使用Eureka服务，可以在配置文件中添加
 * user.service.ribbon.listOfServers=localhost:8081,localhost:8082进行配置
 * <p>
 * 或者为FeignClient手动指定url属性，例 url="http://pay.b.com",而这个这个域名可能已经配置了其他负载均衡器，这个域名对应了多个服务实例
 * <p>
 * 因为是我们这里采用了Eureka,所以默认实现了客户端的负载均衡,可见我们并没有特别的进行负载均衡配置
 * <p>
 * 这个类里的方式 配置没有显示声明,采用的是默认配置;
 */
@FeignClient(name = "user")
public interface UserFeignClient {
    //这里的注解都是用的Spring的注解,因为这个FeignClient 没有显示的指定配置,默认的配置 用的是Spring MVC契约
    @RequestMapping(value = "/path/{id}", method = RequestMethod.GET)
    //@RequestLine("GET /path/{id}")
    public User getUserByPath(@PathVariable("id") Integer id);

    //@RequestLine("GET /path/{id}")
    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public User getUserByParam(@RequestParam("id") Integer id);
}
