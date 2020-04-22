package cn.iocoder.mall.system.biz.dataobject.authorization;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import cn.iocoder.mall.system.biz.dataobject.account.AccountDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * {@link AccountDO} 和 {@link RoleDO} 的关联表
 */
@TableName("admin_role")
@Data
@Accessors(chain = true)
public class AccountRoleDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 账号编号
     *
     * 关联 {@link AccountDO#getId()}
     */
    private Integer accountId;
    /**
     * 角色编号
     *
     * 关联 {@link RoleDO#getId()}
     */
    private Integer roleId;

}
