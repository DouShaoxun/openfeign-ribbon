package cn.cruder.dousx.or.api.expander;

import com.alibaba.fastjson.JSON;
import feign.Param;

/**
 * @author dousx
 * @date 2022-06-26 15:04
 */
public class JsonExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        try {
            return JSON.toJSONString(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
