package cn.cruder.dousx.or.api.feign;

import cn.cruder.dousx.or.api.constant.HttpConstant;
import cn.cruder.dousx.or.api.dto.PostParam;
import cn.cruder.dousx.or.api.dto.PostResult;
import cn.cruder.dousx.or.api.dto.GetResult;
import cn.cruder.dousx.or.api.expander.JsonExpander;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;


/**
 * @author dousx
 * @date 2022-06-17 20:32
 */
public interface ProducerFeignApi {
    /**
     * getTest uri
     */
    String GET_TEST_URI = "/getTest";
    /**
     * postTest uri
     */
    String POST_TEST_URI = "/postTest";

    /**
     * get测试
     *
     * @return {@link GetResult}
     */
    @RequestLine(value = HttpConstant.REQUEST_LINE_GET + GET_TEST_URI)
    GetResult getTest();

    /**
     * post测试
     * <p/>
     * {var} 表示从@Param中取值
     * <br/>
     *
     * @param token authorization取值
     * @param body  {@link PostParam}
     * @return {@link PostResult}
     */
    @RequestLine(value = HttpConstant.REQUEST_LINE_POST + POST_TEST_URI)
    @Headers({
            "authorization: {token}",
            "Content-Type: application/json;charset=utf-8"
    })
    @Body("{body}")
    PostResult postTest(@Param("token") String token, @Param(value = "body", expander = JsonExpander.class) PostParam body);
}
