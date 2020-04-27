package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 资源模块 - 删除资源 DTO
 */
@Data
@Accessors(chain = true)
public class RoleDeleteDTO {

    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;

    @NotNull(message = "角色编号不能为空")
    private Integer id;

}
