package cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * {@link RoleDO} 和 {@link ResourceDO} 的关联表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("permission_role_resource")
public class RoleResourceDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 角色编号(外键：{@link RoleDO}
     */
    private Integer roleId;
    /**
     * 资源编号(外键：{@link ResourceDO}
     */
    private Integer resourceId;

}
