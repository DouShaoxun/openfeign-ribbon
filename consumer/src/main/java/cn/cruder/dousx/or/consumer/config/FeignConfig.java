package cn.cruder.dousx.or.consumer.config;

import cn.cruder.dousx.or.api.feign.ProducerFeignApi;
import cn.cruder.dousx.or.consumer.properties.RibbonProperties;
import cn.cruder.dousx.or.consumer.properties.ServicesUrlProperties;
import com.netflix.client.ClientFactory;
import com.netflix.client.config.ClientConfigFactory;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import feign.Feign;
import feign.Logger;
import feign.Target;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.ribbon.LBClient;
import feign.ribbon.RibbonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dousx
 * @date 2022-06-25 18:51
 */
@Configuration
public class FeignConfig {

    @Bean
    @ConditionalOnClass({ServicesUrlProperties.class, Feign.class})
    public ProducerFeignApi producerFeignApi(Feign feign, ServicesUrlProperties servicesUrlProperties) {
        return feign.newInstance(new Target.HardCodedTarget<>(ProducerFeignApi.class, servicesUrlProperties.getProducer()));
    }

    @Bean
    @ConditionalOnClass({RibbonProperties.class})
    public Feign feign(@Autowired RibbonProperties ribbonProperties) {
        Map<String, List<String>> ribbonInfos = ribbonProperties.getRibbonInfos();
        if (CollectionUtils.isEmpty(ribbonInfos)) {
            throw new RuntimeException("没有ribbon配置");
        }
        RibbonClient ribbonClient = RibbonClient.builder().lbClientFactory(clientName -> {
            BaseLoadBalancer loadBalancer = new BaseLoadBalancer();
            List<String> serverList = ribbonInfos.getOrDefault(clientName, new ArrayList<>());
            for (String hostPort : serverList) {
                String[] split = hostPort.split(":");
                loadBalancer.addServer(new Server(split[0], Integer.parseInt(split[1])));
            }
            IClientConfig producer = ClientFactory.getNamedConfig(clientName, ClientConfigFactory.DEFAULT::newConfig);
            return LBClient.create(loadBalancer, producer);
        }).build();
        return Feign.builder()
                .logger(new Logger.JavaLogger(this.getClass().getName()))
                .client(ribbonClient)
                .logLevel(Logger.Level.FULL)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .build();
    }


}
