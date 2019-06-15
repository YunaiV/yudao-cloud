package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Description:部门实体
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:12
 */
@TableName("deptment")
@Data
@Accessors(chain = true)
public class DeptmentDO extends DeletableDO {

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

}
