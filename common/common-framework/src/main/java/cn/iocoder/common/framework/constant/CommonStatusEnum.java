package cn.iocoder.common.framework.constant;

/**
 * 通用状态枚举
 */
public enum CommonStatusEnum {

    ENABLE(1, "开启"),
    DISABLE(2, "关闭");

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

}