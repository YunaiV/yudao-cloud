package cn.iocoder.mall.system.biz.enums.user;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 用户状态枚举
 */
public enum UserStatusEnum implements IntArrayValuable {

    ENABLED(1, "启用"),
    DISABLED(2, "禁用");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserStatusEnum::getStatus).toArray();

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String name;

    UserStatusEnum(Integer status, String name) {
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
