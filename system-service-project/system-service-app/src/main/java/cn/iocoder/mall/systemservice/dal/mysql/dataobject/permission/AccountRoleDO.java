package cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission;

import cn.iocoder.mall.mybatis.dataobject.DeletableDO;
import cn.iocoder.mall.system.biz.dataobject.account.AccountDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * {@link AccountDO} 和 {@link RoleDO} 的关联表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("account_role")
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
