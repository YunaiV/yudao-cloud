package cn.iocoder.yudao.module.mes.enums.pro;

import cn.iocoder.yudao.framework.common.core.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * MES 生产工单状态枚举
 *
 * @author 芋道源码
 */
@Getter
@AllArgsConstructor
public enum MesProWorkOrderStatusEnum implements ArrayValuable<Integer> {

    PREPARE(0, "草稿"),
    CONFIRMED(1, "已确认"),
    FINISHED(2, "已完成"),
    CANCELED(3, "已取消");

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(MesProWorkOrderStatusEnum::getStatus).toArray(Integer[]::new);

    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    @Override
    public Integer[] array() {
        return ARRAYS;
    }

}
