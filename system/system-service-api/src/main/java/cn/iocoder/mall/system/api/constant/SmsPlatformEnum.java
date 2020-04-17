package cn.iocoder.mall.system.api.constant;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 短信审核状态
 *
 * @author Sin
 * @time 2019/5/16 12:48 PM
 */
public enum SmsPlatformEnum implements IntArrayValuable {

    YunPian(1, "云片"),
    AliYun(2, "阿里云"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(SmsPlatformEnum::getValue).toArray();

    private final Integer value;
    private final String name;

    SmsPlatformEnum(Integer code, String message) {
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
