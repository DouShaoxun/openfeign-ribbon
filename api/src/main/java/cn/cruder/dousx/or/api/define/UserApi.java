package cn.cruder.dousx.or.api.define;

import cn.cruder.dousx.or.api.dto.UserDto;
import feign.Param;
import feign.RequestLine;


/**
 * @author dousx
 * @date 2022-06-17 20:32
 */
public interface UserApi {
    String GET_USER_URI = "/getUser";

    /**
     * get user
     *
     * @return UserDto
     */
    @RequestLine(value = "GET " + GET_USER_URI)
    UserDto getUser();
}
