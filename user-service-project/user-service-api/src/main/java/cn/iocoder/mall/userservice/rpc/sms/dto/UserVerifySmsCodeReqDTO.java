package cn.iocoder.mall.userservice.rpc.sms.dto;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.userservice.enums.sms.UserSmsSceneEnum;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户校验验证码 Request DTO
 */
@Data
@Accessors(chain = true)
@Builder
public class UserVerifySmsCodeReqDTO implements Serializable {

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    private String mobile;
    /**
     * IP
     */
    @NotNull(message = "IP 不能为空")
    private String ip;
    /**
     * 发送场景
     */
    @NotNull(message = "发送场景不能为空")
    @InEnum(value = UserSmsSceneEnum.class, message = "发送场景不能为空")
    private Integer scene;
    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String code;

}
