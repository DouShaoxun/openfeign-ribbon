package cn.cruder.dousx.or.consumer.config;

import cn.cruder.dousx.or.api.define.UserApi;
import cn.cruder.dousx.or.consumer.properties.ServicesUrlProperties;
import feign.Feign;
import feign.Logger;
import feign.Target;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
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
    public UserApi userApi(Feign feign, ServicesUrlProperties servicesUrlProperties) {
        return feign.newInstance(new Target.HardCodedTarget<>(UserApi.class, servicesUrlProperties.getProducer()));
    }

    @Bean
    public Feign feign() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .build();
    }
}
