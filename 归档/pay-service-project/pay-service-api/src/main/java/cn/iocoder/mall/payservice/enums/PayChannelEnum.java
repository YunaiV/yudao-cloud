package cn.iocoder.mall.payservice.enums;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 支付通道
 */
public enum PayChannelEnum implements IntArrayValuable {

    WEIXIN_APP(100, "wx", "微信 App 支付"),
    WEIXIN_PUB(101, "wxjs", "微信 JS API 支付"),

    ALIPAY(200, "alipay", "支付宝 App 支付"),

    PINGXX(9999, "ping++", "ping++ 支付"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(PayChannelEnum::getId).toArray();

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

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
