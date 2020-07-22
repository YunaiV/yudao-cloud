package cn.iocoder.mall.userservice.service.user.bo;

import cn.iocoder.common.framework.validator.Mobile;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 用户创建 BO
 */
@Data
@Accessors(chain = true)
public class UserCreateBO {

    /**
     * 昵称，允许空
     */
    private String nickname;
    /**
     * 头像，允许空
     */
    private String avatar;
    /**
     * 手机
     */
    @NotNull(message = "手机号不能为空")
    @Mobile
    private String mobile;
    /**
     * 原始密码，允许空
     *
     * 当为空时，会自动进行生成
     */
    private String password;
    /**
     * IP 地址
     */
    private String createIp;

}
