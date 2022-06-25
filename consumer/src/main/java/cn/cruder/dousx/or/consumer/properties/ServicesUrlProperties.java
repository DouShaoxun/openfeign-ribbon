package cn.cruder.dousx.or.consumer.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dousx
 * @date 2022-06-25 18:47
 */
@Data
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "services.url")
public class ServicesUrlProperties {
    private String producer;
}


