package cn.iocoder.yudao.module.system.api.oauth2.dto;

import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@ApiModel("RPC 服务 - OAuth2.0 访问令牌创建 Request DTO")
@Data
public class OAuth2AccessTokenCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "用户编号", required = true, example = "10")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @ApiModelProperty(value = "用户类型", required = true, example = "1", notes = "参见 UserTypeEnum 枚举")
    @NotNull(message = "用户类型不能为空")
    @InEnum(value = UserTypeEnum.class, message = "用户类型必须是 {value}")
    private Integer userType;

    @ApiModelProperty(value = "客户端编号", required = true, example = "yudaoyuanma")
    @NotNull(message = "客户端编号不能为空")
    private String clientId;

    @ApiModelProperty(value = "授权范围的数组", example = "user_info")
    private List<String> scopes;

}
