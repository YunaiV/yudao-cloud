package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 角色模块 - 修改角色 DTO
 */
@Data
@Accessors(chain = true)
public class RoleUpdateDTO {

    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;

    @NotNull(message = "角色编号不能为空")
    private Integer id;

    @NotEmpty(message = "角色名字不能为空")
    private String name;

    /**
     * 角色编码
     */
    private String code;

}
