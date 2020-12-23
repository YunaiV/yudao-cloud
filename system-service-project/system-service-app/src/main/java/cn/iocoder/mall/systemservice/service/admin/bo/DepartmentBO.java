package cn.iocoder.mall.systemservice.service.admin.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 部门 BO
*/
@Data
@Accessors(chain = true)
public class DepartmentBO {

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
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标记
     */
    private Integer deleted;

}
