package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description: 部门-角色映射表
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:19
 */
@TableName("deptment_role")
@Data
public class DeptmentRoleDO extends DeletableDO {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 部门id
     */
    private Integer deptmentId;
    /**
     * 角色id
     */
    private Integer roleId;
}
