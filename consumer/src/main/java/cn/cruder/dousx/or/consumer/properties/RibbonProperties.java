package cn.cruder.dousx.or.consumer.properties;

import cn.cruder.dousx.or.consumer.properties.entity.RibbonInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author dousx
 * @date 2022-06-30 21:35
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ribbon-config")
public class RibbonProperties {

    private List<RibbonInfo> ribbonInfos;


}
