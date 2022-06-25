package cn.cruder.dousx.or.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dousx
 * @date 2022-06-17 20:24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements Serializable {
    private String id;
    private String name;
    private Integer age;
}
