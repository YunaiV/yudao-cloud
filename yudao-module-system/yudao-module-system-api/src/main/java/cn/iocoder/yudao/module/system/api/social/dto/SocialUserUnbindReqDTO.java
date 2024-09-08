package cn.iocoder.yudao.module.system.api.social.dto;

import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import cn.iocoder.yudao.module.system.enums.social.SocialTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "RPC 服务 - 取消绑定社交用户 Request DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUserUnbindReqDTO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    @Schema(description = "用户类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(UserTypeEnum.class)
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    @Schema(description = "社交平台的类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer socialType;
    @Schema(description = "社交平台的 openid", requiredMode = Schema.RequiredMode.REQUIRED, example = "zsw")
    @NotEmpty(message = "社交平台的 openid 不能为空")
    private String openid;

}
