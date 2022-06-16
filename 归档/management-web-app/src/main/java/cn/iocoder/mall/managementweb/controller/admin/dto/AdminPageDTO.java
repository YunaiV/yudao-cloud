package cn.iocoder.mall.managementweb.controller.admin.dto;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel("管理员分页查询 DTO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdminPageDTO extends PageParam {

    @ApiModelProperty(value = "真实名字，模糊匹配", example = "小王")
    private String name;

    @ApiModelProperty(value = "部门编号")
    private Integer departmentId;

}
