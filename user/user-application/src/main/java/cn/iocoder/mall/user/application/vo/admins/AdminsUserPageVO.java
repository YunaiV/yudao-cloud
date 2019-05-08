package cn.iocoder.mall.user.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("用户分页 VO")
@Data
@Accessors(chain = true)
public class AdminsUserPageVO {

    @ApiModelProperty(value = "用户数组")
    private List<AdminsUserVO> list;
    @ApiModelProperty(value = "用户总数")
    private Integer total;

}
