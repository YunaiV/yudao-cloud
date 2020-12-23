package cn.iocoder.mall.systemservice.service.permission.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 角色更新 BO
 */
@Data
@Accessors(chain = true)
public class RoleUpdateBO {

    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Integer id;
    /**
     * 角色名
     */
    @NotEmpty(message = "角色名不能为空")
    private String name;
    /**
     * 角色编码
     */
    private String code;

}
