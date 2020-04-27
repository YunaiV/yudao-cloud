package cn.iocoder.mall.system.biz.dto.authorization;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.system.biz.enums.authorization.ResourceTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 资源模块 - 更新资源 DTO
 */
@Data
@Accessors(chain = true)
public class ResourceUpdateDTO {

    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;

    @ApiModelProperty(value = "资源编号", required = true, example = "1")
    @NotNull(message = "资源编号不能为空")
    private Integer id;

    @ApiModelProperty(value = "资源类型。1 代表【菜单】；2 代表【按钮】", required = true, example = "1")
    @NotNull(message = "类型不能为空")
    @InEnum(value = ResourceTypeEnum.class, message = "资源类型必须是 {value}")
    private Integer type;

    @ApiModelProperty(value = "排序", required = true, example = "1")
    @NotNull(message = "类型不能为空")
    private Integer sort;

    @ApiModelProperty(value = "菜单展示名", required = true, example = "商品管理")
    @NotEmpty(message = "资源名字不能为空")
    private String displayName;

    @ApiModelProperty(value = "父级资源编号", required = true, example = "1")
    @NotNull(message = "父级资源编号不能为空")
    private Integer pid;

    @ApiModelProperty(value = "操作", example = "/order/list")
    private String handler;

    @ApiModelProperty(value = "图标", example = "add")
    private String icon;

    @ApiModelProperty(value = "权限标识数组", example = "system.order.add,system.order.update")
    private List<String> permissions;

}
