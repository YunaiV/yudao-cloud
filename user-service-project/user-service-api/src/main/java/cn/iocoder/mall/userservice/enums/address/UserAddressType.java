package cn.iocoder.mall.userservice.enums.address;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 用户收件地址的类型枚举
 */
public enum UserAddressType implements IntArrayValuable {

    DEFAULT(1, "默认收件地址"),
    NORMAL(2, "普通收件地址"), // 即非默认收件笛之爱
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserAddressType::getType).toArray();

    private final Integer type;
    private final String desc;

    UserAddressType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
