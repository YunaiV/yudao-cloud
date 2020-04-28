package cn.iocoder.mall.system.biz.dto.authorization;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.system.biz.enums.authorization.ResourceTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 资源模块 - 更新资源 DTO
 */
@Data
@Accessors(chain = true)
public class ResourceUpdateDTO {

    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;

    @NotNull(message = "资源编号不能为空")
    private Integer id;

    @NotNull(message = "类型不能为空")
    @InEnum(value = ResourceTypeEnum.class, message = "资源类型必须是 {value}")
    private Integer type;

    @NotNull(message = "类型不能为空")
    private Integer sort;

    @NotEmpty(message = "资源名字不能为空")
    private String name;

    @NotNull(message = "父级资源编号不能为空")
    private Integer pid;

    /**
     * 前端路由
     */
    private String route;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限标识
     */
    private String permission;

}
