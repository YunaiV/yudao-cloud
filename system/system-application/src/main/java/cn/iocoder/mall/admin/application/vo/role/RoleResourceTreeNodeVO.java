package cn.iocoder.mall.admin.application.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("角色拥有的资源 VO")
@Data
@Accessors(chain = true)
public class RoleResourceTreeNodeVO {

    @ApiModelProperty(value = "菜单编号", required = true, example = "1")
    private Integer id;
//    @ApiModelProperty(value = "菜单名", required = true, example = "商品管理")
//    private String name;
    @ApiModelProperty(value = "菜单操作", required = true, example = "/order/list")
    private String handler;
    @ApiModelProperty(value = "父菜单编号", required = true, example = "1", notes = "如果无父菜单，则值为 0")
    private Integer pid;
    @ApiModelProperty(value = "排序", required = true, example = "1")
    private Integer sort;
    @ApiModelProperty(value = "菜单展示名", required = true, example = "商品管理")
    private String displayName;
    @ApiModelProperty(value = "子节点数组")
    private List<RoleResourceTreeNodeVO> children;
    @ApiModelProperty(value = "是否授权", required = true, example = "true")
    private Boolean assigned;

}
