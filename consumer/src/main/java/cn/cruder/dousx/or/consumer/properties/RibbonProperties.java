package cn.cruder.dousx.or.consumer.properties;

import com.netflix.loadbalancer.Server;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author dousx
 * @date 2022-06-30 21:35
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ribbon-map-config")
public class RibbonProperties {
    /**
     * <li/> key - clientName
     * <li/> value - host+":"+port,例如:localhost:7070 ,用于构建{@link Server#Server(String, int)}
     */
    private Map<String, List<String>> ribbonInfos;

}
