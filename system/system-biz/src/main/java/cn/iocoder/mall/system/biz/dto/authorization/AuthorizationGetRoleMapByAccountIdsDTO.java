package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * 授权模块 - 获得每个账号所拥有的角色 DTO
 */
@Data
@Accessors(chain = true)
public class AuthorizationGetRoleMapByAccountIdsDTO {

    @NotNull(message = "账号编号数组不能为空")
    private Collection<Integer> accountIds;

}
