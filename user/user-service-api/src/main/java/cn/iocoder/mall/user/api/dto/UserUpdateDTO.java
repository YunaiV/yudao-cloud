package cn.iocoder.mall.user.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户更新 DTO
 */
@Data
@Accessors(chain = true)
public class UserUpdateDTO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;

}
