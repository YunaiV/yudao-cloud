package cn.iocoder.mall.pay.api.constant;

/**
 * 支付通道
 */
public enum PayChannelEnum {

    WEIXIN_APP(100, "wx", "微信 App 支付"),
    WEIXIN_PUB(100, "wx", "微信 JS API 支付"),

    ALIPAY(200, "alipay", "微信支付");

    /**
     * 渠道编号
     */
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 渠道名
     */
    private String name;

    PayChannelEnum(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}