package cn.iocoder.mall.system.api.bo.deptment;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:49
 */
@ApiModel("部门 BO")
@Data
public class DeptmentBO implements Serializable {

    private static final long serialVersionUID = 7656901281539594453L;

    /**
     * 部门编号
     */
    private Integer id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门排序字段
     */
    private Integer sort;
    /**
     * 父级部门id
     */
    private Integer pid;

    private Date createTime;

    private Date updateTime;


}
