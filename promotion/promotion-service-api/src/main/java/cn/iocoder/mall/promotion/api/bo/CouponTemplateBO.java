package cn.iocoder.mall.promotion.api.bo;

import java.util.Date;

public class CouponTemplateBO {

    // ========== 基本信息 BEGIN ==========
    /**
     * 模板编号，自增唯一。
     */
    private Integer id;
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
     * 码类型
     *
     * 1-一卡一码（UNIQUE）
     * 2-通用码（GENERAL）
     *
     * 【优惠码独有】 @see CouponCodeDO
     */
    private Integer codeType;
    /**
     * 优惠码状态
     *
     * 1-开启中
     * 2-禁用中
     * 3-已过期
     *
     * 当优惠劵（码）开启中，可以手动操作，设置禁用中。
     */
    private Integer status;
    /**
     * 每人限领个数
     *
     * null - 则表示不限制
     */
    private Integer quota;
    /**
     * 发放总量
     */
    private Integer total;
    // ========== 领取规则 END ==========

    // ========== 使用规则 BEGIN ==========
    /**
     * 是否设置满多少金额可用，单位：分
     *
     * 0-不限制
     * 大于0-多少金额可用
     */
    private Integer priceAvailable;
    /**
     * 可用范围的类型
     *
     * 10-全部（ALL）：所有可用
     * 20-部分（PART）：部分商品可用，或指定商品可用
     * 21-部分（PART）：部分商品不可用，或指定商品可用
     * 30-部分（PART）：部分分类可用，或指定商品可用
     * 31-部分（PART）：部分分类不可用，或指定商品可用
     */
    private Integer rangeType;
    /**
     * 指定商品 / 分类列表，使用逗号分隔商品编号
     */
    private String rangeValues;
    /**
     * 生效日期类型
     *
     * 1-固定日期
     * 2-领取日期：领到券 {@link #fixedStartTerm} 日开始 N 天内有效
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
    private Integer fixedStartTerm;
    /**
     * 领取日期-结束天数
     */
    private Integer fixedEndTerm;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========
    /**
     * 优惠类型
     *
     * 1-代金卷
     * 2-折扣卷
     */
    private Integer preferentialType;
    /**
     * 折扣百分比。
     *
     * 例如，80% 为 80。
     * 当 100% 为 100 ，则代表免费。
     */
    private Integer percentOff;
    /**
     * 优惠金额，单位：分
     */
    private Integer priceOff;
    /**
     * 折扣上限，仅在 {@link #preferentialType} 等于 2 时生效。
     *
     * 例如，折扣上限为 20 元，当使用 8 折优惠券，订单金额为 1000 元时，最高只可折扣 20 元，而非 80  元。
     */
    private Integer discountPriceLimit;
    // ========== 使用效果 END ==========

    // ========== 统计信息 BEGIN ==========
    /**
     * 领取优惠券的次数
     */
    private Integer statFetchNum;
    // ========== 统计信息 END ==========

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public CouponTemplateBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CouponTemplateBO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CouponTemplateBO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public CouponTemplateBO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public CouponTemplateBO setCodeType(Integer codeType) {
        this.codeType = codeType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CouponTemplateBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getQuota() {
        return quota;
    }

    public CouponTemplateBO setQuota(Integer quota) {
        this.quota = quota;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public CouponTemplateBO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getPriceAvailable() {
        return priceAvailable;
    }

    public CouponTemplateBO setPriceAvailable(Integer priceAvailable) {
        this.priceAvailable = priceAvailable;
        return this;
    }

    public Integer getRangeType() {
        return rangeType;
    }

    public CouponTemplateBO setRangeType(Integer rangeType) {
        this.rangeType = rangeType;
        return this;
    }

    public String getRangeValues() {
        return rangeValues;
    }

    public CouponTemplateBO setRangeValues(String rangeValues) {
        this.rangeValues = rangeValues;
        return this;
    }

    public Integer getDateType() {
        return dateType;
    }

    public CouponTemplateBO setDateType(Integer dateType) {
        this.dateType = dateType;
        return this;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }

    public CouponTemplateBO setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
        return this;
    }

    public Date getValidEndTime() {
        return validEndTime;
    }

    public CouponTemplateBO setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
        return this;
    }

    public Integer getPreferentialType() {
        return preferentialType;
    }

    public CouponTemplateBO setPreferentialType(Integer preferentialType) {
        this.preferentialType = preferentialType;
        return this;
    }

    public Integer getPercentOff() {
        return percentOff;
    }

    public CouponTemplateBO setPercentOff(Integer percentOff) {
        this.percentOff = percentOff;
        return this;
    }

    public Integer getPriceOff() {
        return priceOff;
    }

    public CouponTemplateBO setPriceOff(Integer priceOff) {
        this.priceOff = priceOff;
        return this;
    }

    public Integer getDiscountPriceLimit() {
        return discountPriceLimit;
    }

    public CouponTemplateBO setDiscountPriceLimit(Integer discountPriceLimit) {
        this.discountPriceLimit = discountPriceLimit;
        return this;
    }

    public Integer getStatFetchNum() {
        return statFetchNum;
    }

    public CouponTemplateBO setStatFetchNum(Integer statFetchNum) {
        this.statFetchNum = statFetchNum;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CouponTemplateBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getFixedStartTerm() {
        return fixedStartTerm;
    }

    public CouponTemplateBO setFixedStartTerm(Integer fixedStartTerm) {
        this.fixedStartTerm = fixedStartTerm;
        return this;
    }

    public Integer getFixedEndTerm() {
        return fixedEndTerm;
    }

    public CouponTemplateBO setFixedEndTerm(Integer fixedEndTerm) {
        this.fixedEndTerm = fixedEndTerm;
        return this;
    }

}
