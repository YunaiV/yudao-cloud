package cn.iocoder.mall.promotion.biz.dataobject;

import java.util.Date;

/**
 * 优惠劵（码）模板。
 *
 * 当用户领取时，会生成 {@link Coupon} 优惠劵（码）。
 */
public class CouponTemplate {

    // ========== 基本信息 BEGIN ==========
    /**
     * 编号，自增唯一。
     */
    private Integer id;
    /**
     * 店铺编号
     */
    private Integer shopId;
    /**
     * 别名
     *
     * 系统生成，作为唯一标识。例如，2fpa62tbmsl9h
     * 可以用于生成优惠券（码）领取链接，例如，https://wap.youzan.com/v2/showcase/coupon/fetch?alias=17xcvjbd8
     */
    private String alias;
    /**
     * 标题
     */
    private String title;
    /**
     * 使用说明
     */
    private String description;
    /**
     * 类型
     *
     * 1-优惠劵
     * 2-优惠码
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 优惠码状态
     *
     * 1-生效中
     * 2-已失效
     * 3-已过期
     * 4-已删除
     *
     * 当优惠劵（码）有效时，可以手动操作，设置成无效。
     */
    private Integer status;
    /**
     * 是否可分享领取链接
     */
    private Boolean isShare;
    /**
     * 设置为失效时间
     */
    private Date invalidTime;
    /**
     * 删除时间
     */
    private Date deleteTime;

    // ========== 基本信息 END ==========

    // ========== 码信息 BEGIN ==========
    /**
     * 码类型
     *
     * 1-一卡一码（UNIQUE）
     * 2-通用码（GENERAL）
     *
     * 【优惠码独有】
     */
    private Integer codeType;
    /**
     * 优惠码
     *
     * 【优惠码独有】
     */
    private String code;
    // ========== 码信息 END ==========

    // ========== 领取规则 BEGIN ==========
    /**
     * 是否限制领用者的等级
     *
     * 0-不限制
     * 大于0-领用者必须是这个等级编号
     *
     * 【优惠劵独有】
     */
    private Integer needUserLevel;
    /**
     * 每人限领个数
     *
     * 0-则表示不限制
     */
    private Integer quota;
    /**
     * 剩余可用库存
     */
    private Integer stock;
    /**
     * 总库存
     */
    private Integer total;
    /**
     * 领取优惠券要给用户打上的标签的列表，使用逗号分隔标签编号
     */
    private String markTags;
    // ========== 领取规则 END ==========

    // ========== 使用规则 BEGIN ==========
    /**
     * 是否仅原价购买商品时可用
     *
     * true-是
     * false-否
     */
    private Boolean isForbidPreference;
    /**
     * 是否设置满多少金额可用，单位：分
     *
     * 0-不限制
     * 大于0-多少金额可用
     */
    private Integer condition;
    /**
     * 可用范围的类型
     *
     * 1-部分（ALL）：全部商品可用
     * 2-全部（PART）：部分商品可用，或指定商品可用
     */
    private Integer rangeType;
    /**
     * 指定可用商品列表，使用逗号分隔商品编号 {@link cn.iocoder.doraemon.itemgroup.item.entity.Item#id}
     */
    private String rangeValues;
    /**
     * 生效日期类型
     *
     * 1-固定日期
     * 2-领取日期：领到券 {@link #fixedBeginTerm} 日开始 N 天内有效
     */
    private Integer dateType;
    /**
     * 固定日期-生效开始时间
     */
    private Date validStartTime;
    /**
     * 固定日期-生效结束时间
     */
    private Date validEndTime;
    /**
     * 领取日期-开始天数
     *
     * 例如，0-当天；1-次天
     */
    private Integer fixedBeginTerm;
    /**
     * 领取日期-结束天数
     */
    private Integer fixedTerm;
    /**
     * 是否到期前4天发送提醒
     *
     * true-发送
     * false-不发送
     */
    private Boolean expireNotice;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========
    /**
     * 优惠类型
     *
     * 1-代金卷
     * 2-折扣卷 【优惠劵独有】
     */
    private Integer preferentialType;
    /**
     * 折扣。例如，80% 为 80。
     *
     *【优惠劵独有】
     */
    private Integer discount;
    /**
     * 是否是随机优惠券
     *
     * true-随机
     * false-不随机
     *
     * 【优惠劵独有】
     */
    private Boolean isRandom;
    /**
     * 优惠金额，单位：分
     *
     * 当 {@link #isRandom} 为 true 时，代表随机优惠金额的下限
     */
    private Integer value;
    /**
     * 优惠金额上限
     *
     * 【优惠劵独有】
     */
    private Integer valueRandomTo;
    // ========== 使用效果 END ==========

    // ========== 统计信息 BEGIN ==========
    /**
     * 领取优惠券的人数
     */
    private Integer statFetchUserNum;
    /**
     * 领取优惠券的次数
     */
    private Integer statFetchNum;
    /**
     * 使用优惠券的次数
     */
    private Integer statUseNum;
    // ========== 统计信息 END ==========

    // ========== 微信卡卷 BEGIN ==========
    /**
     * 是否同步微信卡券
     *
     * true-是
     * false-否
     */
    private Boolean isSyncWeixin;
    /**
     * 同步微信卡券，选择的卡券颜色的值。
     *
     * 例如，Color10
     */
    private String weixinColor;
    /**
     * 同步微信卡券，选择的卡券颜色的RGB值
     *
     * 例如，#ffaaff
     */
    private String weixinColorRGB;
    /**
     * 同步微信卡券，卡券的标题
     */
    private String weixinTitle;
    /**
     * 同步微信卡券，卡券的副标题
     */
    private String weixinSubTitle;
    /**
     * 同步微信卡券，卡券的客服电话
     */
    private String servicePhone;
    /**
     * 同步微信卡券，设置是否可以转赠
     *
     * true-可以
     * false-不可以
     */
    private Boolean canGiveFriend;
    // ========== 微信卡卷 END ==========

}