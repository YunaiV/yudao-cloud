package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 授权模块 - 获得账号所拥有的资源树 DTO
 */
@Data
@Accessors(chain = true)
public class AuthorizationGetResourceTreeByAccountIdDTO {

    @NotNull(message = "账号编号不能为空")
    private Integer accountId;
    /**
     * 资源类型
     */
    private Integer type;

}
