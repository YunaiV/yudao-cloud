package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("管理员分页 VO")
@Data
@Accessors(chain = true)
public class AdminPageVO {

    @ApiModelProperty(value = "管理员数组")
    private List<AdminVO> list;
    @ApiModelProperty(value = "管理员总数")
    private Integer total;

}
