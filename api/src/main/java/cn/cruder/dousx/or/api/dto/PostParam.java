package cn.cruder.dousx.or.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dousx
 * @date 2022-06-25 21:08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostParam implements Serializable {
    private String id;
    private String name;
    private Integer age;
}
