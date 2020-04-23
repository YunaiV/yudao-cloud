package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * 授权模块 - 校验账号是否有权限 DTO
 */
@Data
@Accessors(chain = true)
public class AuthorizationCheckPermissionsDTO {

    @NotNull(message = "账号编号不能为空")
    private Integer accountId;
    @NotNull(message = "权限不能为空")
    private Collection<String> permissions;

}
