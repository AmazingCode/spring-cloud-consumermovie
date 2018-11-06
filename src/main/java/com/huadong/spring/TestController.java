package com.huadong.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController()
public class TestController {

    /**
     * 用户在eureka定义的服务名
     */
    private final String userServiceName="user";

    /**
     * 实现了 客户端负载均衡 的restTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * eureka 客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 负载均 衡客户端
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    /**
     * 通过写死IP与端口访问User的接口
     * @param id
     * @return
     */
    @RequestMapping(value = "movie1/{id}", method = RequestMethod.GET)
    public User getUserByIp(@PathVariable(value = "id") Integer id) {

       //这里会报错，因为使用的是restTemplate，在Eureka里找不到localhost这个注册的信息
       return restTemplate.getForObject("http://localhost:8081/name/"+id,User.class);

    }

    /**
     * 通过 User服务名 访问User的接口
     * @param id
     * @return
     */
    @RequestMapping(value = "movie2/{id}", method = RequestMethod.GET)
    public User getUserByServiceName(@PathVariable(value = "id") Integer id) {

        return restTemplate.getForObject("http://"+userServiceName+"/name/"+id,User.class);

    }
    /**
     * 通过服务名获取User服务的信息，其中包括User配置文件中自定义的配置字段
     * @return
     */
    @RequestMapping(value = "fields", method = RequestMethod.GET)
    public List<ServiceInstance> getUserInstanceField() {

        return this.discoveryClient.getInstances(userServiceName);
    }

    /**
     * 检查在负载均衡的作用下，此次请求访问的是那个实例
     * @return
     */
    @RequestMapping(value = "test/instance", method = RequestMethod.GET)
    public String testLoadInstance() {

        ServiceInstance instance =  this.loadBalancerClient.choose(userServiceName);
        return  String.format("host:%s;port:%s",instance.getHost(),instance.getPort());
    }
}
