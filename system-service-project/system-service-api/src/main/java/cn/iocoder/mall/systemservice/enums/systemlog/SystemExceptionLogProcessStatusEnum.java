package cn.iocoder.mall.systemservice.enums.systemlog;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 系统异常日志的处理状态枚举
 */
public enum SystemExceptionLogProcessStatusEnum implements IntArrayValuable {

    INIT(0, "未处理"),
    DONE(1, "已处理"),
    IGNORE(2, "已忽略");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(SystemExceptionLogProcessStatusEnum::getStatus).toArray();

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 资源类型名
     */
    private final String name;

    SystemExceptionLogProcessStatusEnum(Integer status, String name) {
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
