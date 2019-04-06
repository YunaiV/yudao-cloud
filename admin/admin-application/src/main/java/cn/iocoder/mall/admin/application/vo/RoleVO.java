package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("角色 VO")
@Data
@Accessors(chain = true)
public class RoleVO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "角色名字", required = true, example = "系统管理员")
    private String name;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
