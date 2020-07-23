package cn.iocoder.mall.userservice.dal.mysql.dataobject.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户三方开放平台授权，例如：QQ / 微博 / 微信等等。
 *
 * TODO 优化点：需要在改改
 */
@Data
@Accessors(chain = true)
public class UserThirdAuthDO {

    /**
     * 用户编号
     *
     * 外键 {@link UserDO#uid}
     */
    private Long uid;

    // ========== 授权相关字段

    /**
     * 用户的唯一标识
     */
    private String openid;
    /**
     * 开放平台
     *
     * @see cn.iocoder.mall.user.api.constant.ThirdPlatformConstant
     */
    private Integer platform;
    /**
     * 访问令牌
     */
    private Date accessToken;
    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * 刷新令牌
     */
    private Date refreshToken;
    /**
     * 授权范围。一般情况下，使用逗号分隔
     */
    private String scopes;

    // ========== 基础信息
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 性别
     *
     * TODO 芋艿，找地方统一枚举。0-未知，1-男，2-女
     */
    private Integer gender;
    // TODO https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
    // TODO 芋艿，其他字段，国家/省份/城市/地区等
    // TODO 芋艿，头像
    // TODO 芋艿，微信独有 unionid
    /**
     * 统一存储基础信息，使用 JSON 格式化，避免未有效解析的情况。
     */
    private String extras;

}
