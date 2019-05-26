package cn.iocoder.mall.admin.api.constant;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 短信审核状态
 *
 * @author Sin
 * @time 2019/5/16 12:48 PM
 */
public enum SmsTypeEnum implements IntArrayValuable {

    VERIFICATION_CODE(1, "验证码"),
    NOTICE(1, "通知"),
    MARKETING(2, "营销"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(SmsTypeEnum::getValue).toArray();

    private final Integer value;
    private final String name;

    SmsTypeEnum(Integer code, String message) {
        this.value = code;
        this.name = message;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
