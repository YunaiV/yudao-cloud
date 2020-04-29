package cn.iocoder.mall.system.rest.response.authorization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel(value = "管理员 - 授权模块 - 角色拥有的资源树 Response")
@Data
@Accessors(chain = true)
public class AdminsAuthorizationRoleResourceTreeResponse {

    @ApiModelProperty(value = "菜单编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "菜单名", required = true, example = "商品管理")
    private String name;
    @ApiModelProperty(value = "是否分配", required = true, notes = "即角色是否拥有该资源")
    private Boolean assigned;
    /**
     * 子节点数组
     */
    private List<AdminsAuthorizationRoleResourceTreeResponse> children;

}
