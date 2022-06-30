package cn.cruder.dousx.or.consumer.config;

import cn.cruder.dousx.or.api.feign.ProducerFeignApi;
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
import feign.ribbon.LBClientFactory;
import feign.ribbon.RibbonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dousx
 * @date 2022-06-25 18:51
 */
@Configuration
public class FeignConfig {

    @Bean
    @ConditionalOnClass({ServicesUrlProperties.class, Feign.Builder.class})
    public ProducerFeignApi producerFeignApi(Feign feign, ServicesUrlProperties servicesUrlProperties) {
        return feign.newInstance(new Target.HardCodedTarget<>(ProducerFeignApi.class, servicesUrlProperties.getProducer()));
    }

    @Bean
    public Feign feign() {
        RibbonClient ribbonClient = RibbonClient.builder().lbClientFactory(new LBClientFactory() {
            @Override
            public LBClient create(String clientName) {
                BaseLoadBalancer loadBalancer = new BaseLoadBalancer();
                // TODO: 2022.06.30 根据 clientName从配置中读取
                loadBalancer.addServer(new Server("127.0.0.1", 7070));
                loadBalancer.addServer(new Server("127.0.0.1", 7071));
                IClientConfig producer = ClientFactory.getNamedConfig("producer", ClientConfigFactory.DEFAULT::newConfig);
                LBClient lbClient = LBClient.create(loadBalancer, producer);
                return lbClient;
            }
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
