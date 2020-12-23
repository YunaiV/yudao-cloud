package cn.iocoder.mall.systemservice.service.permission.bo;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.systemservice.enums.permission.ResourceTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* 资源创建 BO
*/
@Data
@Accessors(chain = true)
public class ResourceCreateBO {

    /**
     * 菜单名
     */
    @NotEmpty(message = "菜单名不能为空")
    private String name;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 资源类型
     */
    @NotNull(message = "资源类型不能为空")
    @InEnum(value = ResourceTypeEnum.class, message = "资源类型必须是 {value}")
    private Integer type;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;
    /**
     * 父级资源编号
     */
    @NotNull(message = "父级资源编号不能为空")
    private Integer pid;
    /**
     * 前端路由
     */
    private String route;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 前端界面
     */
    private String view;

    /**
     * 创建管理员编号
     */
    @NotNull(message = "创建管理员编号不能为空")
    private Integer createAdminId;


}
