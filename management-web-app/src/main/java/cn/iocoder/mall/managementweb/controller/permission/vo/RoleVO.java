package cn.iocoder.mall.managementweb.controller.permission.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("角色 VO")
@Data
public class RoleVO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "角色名", required = true, example = "管理员")
    private String name;
    @ApiModelProperty(value = "角色编码", example = "ADMIN")
    private String code;
    @ApiModelProperty(value = "角色类型", required = true, example = "1", notes = "见 RoleTypeEnum 枚举")
    private Integer type;
    @ApiModelProperty(value = "创建管理员编号", required = true, example = "1")
    private Integer createAdminId;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
