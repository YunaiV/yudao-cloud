package cn.iocoder.mall.user.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户实体，存储用户基本数据。
 *
 * idx_mobile 唯一索引
 */
@Data
@Accessors(chain = true)
public class UserDO extends DeletableDO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 账号状态
     *
     * 1 - 开启
     * 2 - 禁用
     */
    private Integer status;


}
