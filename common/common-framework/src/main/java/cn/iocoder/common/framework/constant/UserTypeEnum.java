package cn.iocoder.common.framework.constant;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 全局用户类型枚举
 */
public enum UserTypeEnum implements IntArrayValuable {

    USER(1, "用户"),
    ADMIN(2, "管理员");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserTypeEnum::getValue).toArray();

    /**
     * 类型
     */
    private Integer value;
    /**
     * 类型名
     */
    private String name;

    UserTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public UserTypeEnum setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserTypeEnum setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
