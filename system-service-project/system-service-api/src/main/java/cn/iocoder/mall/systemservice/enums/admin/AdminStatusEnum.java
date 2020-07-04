package cn.iocoder.mall.systemservice.enums.admin;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 管理员的状态枚举
 */
public enum AdminStatusEnum implements IntArrayValuable {

    ACTIVE(1, "在职"),
    INACTIVE(2, "离职");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(AdminStatusEnum::getStatus).toArray();

    /**
     * 在职状态
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String name;

    AdminStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
