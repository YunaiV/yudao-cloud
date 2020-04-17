package cn.iocoder.mall.system.biz.dataobject.admin;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import cn.iocoder.mall.system.biz.dataobject.account.AccountDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 管理员实体
 */
@TableName(value = "admin")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdminDO extends DeletableDO {

    /**
     * 管理员编号
     */
    private Integer id;
    /**
     * 账号编号
     *
     * 关联 {@link AccountDO#getId()}
     */
    private Integer accountId;
    /**
     * 真实名字
     */
    private String name;
    /**
     * 科室编号
     *
     * 关联 {@link DepartmentDO#getId()}
     */
    private Integer departmentId;

}
