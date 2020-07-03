package cn.iocoder.mall.userservice.service.user.bo;

import cn.iocoder.common.framework.constant.CommonStatusEnum;

/**
 * 用户信息
 */
public class UserBO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 经过加密的密码串
     */
    private String password;
    /**
     * {@link #password} 的盐
     */
    private String passwordSalt;

}
