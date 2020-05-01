package cn.iocoder.mall.system.rest.response.authorization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("管理员 - 角色模块 - 分页列表 Response")
@Data
@Accessors(chain = true)
public class AdminsRolePageResponse {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "角色名字", required = true, example = "管理员")
    private String name;
    @ApiModelProperty(value = "角色编码", example = "SUPER_ADMIN")
    private String code;
    @ApiModelProperty(value = "角色类型", required = true, example = "1-系统角色; 2-内置角色")
    private Integer type;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
