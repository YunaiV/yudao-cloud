package cn.iocoder.mall.admin.api.dto.depetment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-26
 * @time: 22:37
 */
@ApiModel("部门更新DTO")
@Data
public class DeptmentUpdateDTO implements Serializable {

    private static final long serialVersionUID = 8905970407110374395L;

    @ApiModelProperty(value = "部门id", required = true, example = "1")
    @NotNull(message = "部门id不能为空")
    private Integer id;

    @ApiModelProperty(value = "部门名字", required = true, example = "销售部")
    @NotEmpty(message = "部门名字不为空")
    private String name;

    @ApiModelProperty(value = "部门排序", required = true, example = "1")
    @NotNull(message = "部门排序不为空")
    private Integer sort;

    @ApiModelProperty(value = "父级部门id", required = true, example = "1")
    private Integer pid;

}
