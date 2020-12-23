package cn.iocoder.mall.userservice.service.user.bo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.validator.Mobile;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
* 用户更新 BO
*/
@Data
@Accessors(chain = true)
public class UserUpdateBO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态
     */
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;
    /**
     * 手机号
     */
    @Mobile
    private String mobile;
    /**
     * 密码
     */
    private String password;

}
