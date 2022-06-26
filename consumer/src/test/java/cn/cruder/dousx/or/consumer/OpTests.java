package cn.cruder.dousx.or.consumer;

import cn.cruder.dousx.or.api.feign.ProducerFeignApi;
import cn.cruder.dousx.or.api.dto.PostParam;
import cn.cruder.dousx.or.api.dto.PostResult;
import cn.cruder.dousx.or.api.dto.GetResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * <a href="https://github.com/OpenFeign/feign">GitHub OpenFeign </a>
 *
 * @author dousx
 * @date 2022-06-25 18:20
 */
@SpringBootTest
@Slf4j
public class OpTests {
    @Autowired
    ProducerFeignApi producerFeignApi;

    @Test
    void testGet() {
        GetResult user = producerFeignApi.getTest();
        log.info(JSON.toJSONString(user));
    }

    @Test
    void testPost() {
        String token = "bran - 123";
        PostParam param = new PostParam(UUID.randomUUID().toString(), "tom", 19);
        PostResult postResult = producerFeignApi.postTest(token, param);
        log.info(JSON.toJSONString(postResult));

    }
}
