package cn.iocoder.mall.systemservice.enums.errorcode;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 错误码的类型枚举
 *
 * 考虑到便利性，我们会扫描每个项目的错误码枚举类，自动添加到错误码数据库中，并标记为 {@link #AUTO_GENERATION} 类型
 * 经过管理员手动编辑过的错误码，会标记为 {@link #MANUAL_OPERATION} 类型，并禁止自动同步
 *
 * @author ding
 */
public enum ErrorCodeTypeEnum implements IntArrayValuable {

    /**
     * 自动生成
     */
    AUTO_GENERATION(1),
    /**
     * 手动编辑
     */
    MANUAL_OPERATION(2);

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(ErrorCodeTypeEnum::getType).toArray();

    private final Integer type;

    ErrorCodeTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
