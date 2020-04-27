package cn.iocoder.mall.system.rest.response.authorization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("管理员 - 授权模块 - 菜单资源树")
@Data
@Accessors(chain = true)
public class AdminsResourceTreeResponse {

    @ApiModelProperty(value = "菜单编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "菜单名", required = true, example = "商品管理")
    private String name;
    @ApiModelProperty(value = "权限标识", example = "/order/list")
    private String permission;
    @ApiModelProperty(value = "资源类型", required = true, example = "1-菜单；2-按钮")
    private Integer type;
    @ApiModelProperty(value = "排序", required = true, example = "1")
    private Integer sort;
    @ApiModelProperty(value = "父菜单编号", required = true, example = "1", notes = "如果无父菜单，则值为 0")
    private Integer pid;
    @ApiModelProperty(value = "前端路由", example = "/order/list")
    private String route;
    @ApiModelProperty(value = "菜单图标", example = "user")
    private String icon;
    /**
     * 子节点数组
     */
    private List<AdminsResourceTreeResponse> children;

}
