package cn.iocoder.mall.systemservice.rpc.admin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* 部门创建 DTO
*/
@Data
@Accessors(chain = true)
public class DepartmentCreateDTO implements Serializable {

    /**
     * 部门名称
     */
    @NotEmpty(message = "部门名称不能为空")
    private String name;
    /**
     * 排序字段
     */
    @NotNull(message = "排序字段不能为空")
    private Integer sort;
    /**
     * 父级部门编号
     */
    @NotNull(message = "父级部门编号不能为空")
    private Integer pid;

}
