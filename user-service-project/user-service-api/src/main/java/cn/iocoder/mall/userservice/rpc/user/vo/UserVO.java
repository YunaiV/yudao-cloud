package cn.iocoder.mall.userservice.rpc.user.vo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户响应
 */
@Data
@Accessors(chain = true)
public class UserVO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
