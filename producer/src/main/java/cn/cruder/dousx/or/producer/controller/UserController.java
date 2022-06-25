package cn.cruder.dousx.or.producer.controller;

import cn.cruder.dousx.or.api.define.UserApi;
import cn.cruder.dousx.or.api.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

/**
 * @author dousx
 * @date 2022-06-17 20:55
 */
@Slf4j
@RestController
public class UserController implements UserApi {


    @GetMapping(UserApi.GET_USER_URI)
    @Override
    public UserDto getUser() {
        String id = UUID.randomUUID().toString();
        int age = new Random().nextInt(50);
        UserDto userDto = UserDto.builder().name("tom").age(age).id(id).build();
        log.info("id:{} ,user:{}", id, userDto.toString());
        return userDto;
    }
}
