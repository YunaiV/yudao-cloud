package cn.iocoder.mall.systemservice.service.permission.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* 角色创建 BO
*/
@Data
@Accessors(chain = true)
public class RoleCreateBO {

    /**
     * 角色名
     */
    @NotEmpty(message = "角色名不能为空")
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 创建管理员编号
     */
    @NotNull(message = "创建管理员编号不能为空")
    private Integer createAdminId;

}
