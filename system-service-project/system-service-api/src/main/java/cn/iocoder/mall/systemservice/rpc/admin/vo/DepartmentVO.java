package cn.iocoder.mall.systemservice.rpc.admin.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* 部门 VO
*/
@Data
@Accessors(chain = true)
public class DepartmentVO implements Serializable {

    /**
     * 部门编号
     */
    private Integer id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序字段
     */
    private Integer sort;
    /**
     * 父级部门编号
     */
    private Integer pid;
    /**
     * 创建时间
     */
    private Date createTime;

}
