package cn.iocoder.mall.system.rpc.request.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 授权模块 - 校验账号是否有权限 Request
 */
@Data
@Accessors(chain = true)
public class AuthorizationCheckPermissionsRequest implements Serializable {

    @NotNull(message = "账号不能为空")
    private Integer accountId;
    @NotNull(message = "校验的权限不能为空")
    private List<String> permissions;

}
