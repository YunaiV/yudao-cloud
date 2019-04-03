package cn.iocoder.common.framework.constant;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 通用状态枚举
 */
public enum CommonStatusEnum implements IntArrayValuable {

    ENABLE(1, "开启"),
    DISABLE(2, "关闭");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CommonStatusEnum::getValue).toArray();

    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

    CommonStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public CommonStatusEnum setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public CommonStatusEnum setName(String name) {
        this.name = name;
        return this;
    }

    public static boolean isValid(Integer status) {
        if (status == null) {
            return false;
        }
        return ENABLE.value.equals(status)
                || DISABLE.value.equals(status);
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
