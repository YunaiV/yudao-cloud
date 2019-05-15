package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * {@link AdminDO} 和 {@link RoleDO} 的关联表
 */
@TableName("admin_role")
@Data
@Accessors(chain = true)
public class AdminRoleDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 管理员编号(外键：{@link AdminDO}
     */
    private Integer adminId;
    /**
     * 角色编号(外键：{@link RoleDO}
     */
    private Integer roleId;

}
