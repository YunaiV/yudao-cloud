package cn.iocoder.mall.system.biz.dataobject.user;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import cn.iocoder.mall.system.biz.dataobject.account.AccountDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户实体
 */
@TableName(value = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserDO extends DeletableDO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 账号编号
     *
     * 关联 {@link AccountDO#getId()}
     */
    private Integer accountId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;

}
