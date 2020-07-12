package cn.iocoder.mall.managementweb.controller.passport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel(value = "管理员拥有的菜单树", description = "一般用于首页菜单")
@Data
@Accessors(chain = true)
public class PassportAdminMenuTreeNodeVO {

    @ApiModelProperty(value = "菜单编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "菜单名", required = true, example = "商品管理")
    private String name;
    @ApiModelProperty(value = "前端路由", required = true, example = "/order/list")
    private String route;
    @ApiModelProperty(value = "菜单图标", required = true, example = "user")
    private String icon;
    @ApiModelProperty(value = "前端界面", example = "@/views/example/edit")
    private String view;
    @ApiModelProperty(value = "父级资源编号", required = true, example = "1", notes = "如果无父资源，则值为 0")
    private Integer pid;

    /**
     * 子节点数组
     */
    private List<PassportAdminMenuTreeNodeVO> children;

}
