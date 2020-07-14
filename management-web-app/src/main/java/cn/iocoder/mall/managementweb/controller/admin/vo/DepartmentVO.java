package cn.iocoder.mall.managementweb.controller.admin.vo;

import lombok.*;
import io.swagger.annotations.*;
import java.util.*;

@ApiModel("部门 VO")
@Data
public class DepartmentVO {

    @ApiModelProperty(value = "部门编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "部门名称", required = true, example = "技术部")
    private String name;
    @ApiModelProperty(value = "排序字段", required = true, example = "1024")
    private Integer sort;
    @ApiModelProperty(value = "父级部门编号", required = true, example = "2048")
    private Integer pid;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
