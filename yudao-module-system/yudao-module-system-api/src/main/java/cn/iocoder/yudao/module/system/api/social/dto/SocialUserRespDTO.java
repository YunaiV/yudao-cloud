package cn.iocoder.yudao.module.system.api.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "RPC 服务 - 社交用户 Response DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUserRespDTO {

    @Schema(description = "社交用户 openid", requiredMode = Schema.RequiredMode.REQUIRED, example = "zsw")
    private String openid;

    @Schema(description = "社交用户的昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道源码")
    private String nickname;

    @Schema(description = "社交用户的头像", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn/1.jpg")
    private String avatar;

    @Schema(description = "关联的用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long userId;

}
