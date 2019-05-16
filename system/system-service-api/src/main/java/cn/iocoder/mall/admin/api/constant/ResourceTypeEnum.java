package cn.iocoder.mall.admin.api.constant;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 资源类型枚举
 */
public enum ResourceTypeEnum implements IntArrayValuable {

    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(ResourceTypeEnum::getValue).toArray();

    /**
     * 资源类型
     */
    private Integer value;
    /**
     * 资源类型名
     */
    private String name;

    ResourceTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public ResourceTypeEnum setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourceTypeEnum setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
