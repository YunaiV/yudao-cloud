package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@ApiModel("管理员信息 VO")
@Data
@Accessors(chain = true)
public class AdminInfoVO {

    @ApiModelProperty(value = "管理员比那好", required = true, example = "1")
    private Integer adminId;
    @ApiModelProperty(value = "角色编号的数组", required = true, example = "[1, 2]")
    private Set<Integer> roleIds;

}
