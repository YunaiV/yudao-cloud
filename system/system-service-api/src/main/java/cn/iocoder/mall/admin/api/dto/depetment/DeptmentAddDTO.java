package cn.iocoder.mall.admin.api.dto.depetment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:55
 */
@ApiModel("添加部门 DTO")
@Data
public class DeptmentAddDTO {

    @ApiModelProperty(value = "名字", required = true, example = "销售一组")
    @NotNull(message = "不能为空")
    private String name;

    @ApiModelProperty(value = "排序", required = true, example = "1")
    @NotNull(message = "不能为空")
    private Integer sort;

    @ApiModelProperty(value = "父级id", required = true, example = "1")
    @NotNull(message = "可以为空，默认0，顶层")
    @Min(value = 0,message = "父id不能小于0")
    private Integer pid = 0;


}
