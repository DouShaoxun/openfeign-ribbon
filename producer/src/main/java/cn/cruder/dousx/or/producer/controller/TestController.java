package cn.cruder.dousx.or.producer.controller;

import cn.cruder.dousx.or.api.dto.GetResult;
import cn.cruder.dousx.or.api.dto.PostParam;
import cn.cruder.dousx.or.api.dto.PostResult;
import cn.cruder.dousx.or.api.feign.ProducerFeignApi;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;

/**
 * @author dousx
 * @date 2022-06-17 20:55
 */
@Slf4j
@RestController
public class TestController {
    @Value("${server.port:8080}")
    private Integer serverPort;

    /**
     * get测试
     *
     * @return {@link GetResult}
     */
    @GetMapping(ProducerFeignApi.GET_TEST_URI)
    public GetResult get() {
        String id = UUID.randomUUID().toString();
        int age = new Random().nextInt(50);
        GetResult getResult = GetResult.builder().name("tom").age(age).id(id).serverPort(serverPort).build();
        log.info("id:{} ,user:{}", id, getResult.toString());
        return getResult;
    }


    /**
     * post测试
     *
     * @return {@link GetResult}
     */
    @PostMapping(ProducerFeignApi.POST_TEST_URI)
    public PostResult post(@RequestBody PostParam param) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        String authorization = request.getHeader("authorization");
        log.info("param:{} ,authorization:{}", JSON.toJSONString(param), authorization);
        PostResult postResult = new PostResult();
        BeanUtils.copyProperties(param, postResult);
        postResult.setAge(postResult.getAge() + 10);
        postResult.setServerPort(serverPort);
        return postResult;
    }
}
