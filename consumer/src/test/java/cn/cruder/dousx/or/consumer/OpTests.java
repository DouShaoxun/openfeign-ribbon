package cn.cruder.dousx.or.consumer;

import cn.cruder.dousx.or.api.define.UserApi;
import cn.cruder.dousx.or.api.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    UserApi userApi;

    @Test
    void testGetUser() {
        UserDto user = userApi.getUser();
        log.info(user.toString());
    }
}
