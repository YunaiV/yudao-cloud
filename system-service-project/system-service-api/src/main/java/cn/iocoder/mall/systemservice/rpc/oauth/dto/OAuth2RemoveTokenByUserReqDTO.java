package cn.iocoder.mall.systemservice.rpc.oauth.dto;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * OAuth2.0 移除用户令牌 Request DTO
 *
 * 包括访问令牌和刷新令牌
 */
@Data
@Accessors(chain = true)
public class OAuth2RemoveTokenByUserReqDTO implements Serializable {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 用户类型
     */
    @NotNull(message = "用户类型不能为空")
    @InEnum(value = UserTypeEnum.class, message = "用户类型必须是 {value}")
    private Integer userType;

}
