package cn.iocoder.mall.system.rest.response.authorization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("管理员 - 授权模块 - 菜单资源树")
@Data
@Accessors(chain = true)
public class AdminsAuthorizationMenuTreeResponse {

    @ApiModelProperty(value = "菜单编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "菜单名", required = true, example = "商品管理")
    private String name;
    @ApiModelProperty(value = "前端路由", required = true, example = "/order/list")
    private String route;
    @ApiModelProperty(value = "菜单图标", required = true, example = "user")
    private String icon;
    @ApiModelProperty(value = "子节点数组")
    private List<AdminsAuthorizationMenuTreeResponse> children;

}
