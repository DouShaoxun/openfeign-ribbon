package cn.cruder.dousx.or.consumer.properties.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author dousx
 * @date 2022-06-30 21:34
 */
@Getter
@Setter
public class RibbonInfo {
    /**
     * 服务名称
     */
    private String clientName;

    /**
     * 服务配置列表
     */
    private List<String> serverList;


}
