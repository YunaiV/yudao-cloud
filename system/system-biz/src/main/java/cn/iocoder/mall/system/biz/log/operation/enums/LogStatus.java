package cn.iocoder.mall.system.biz.log.operation.enums;

/**
 * @author Hccake
 * @version 1.0
 * @date 2020/5/15 14:47
 */
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作状态
 */
@Getter
@AllArgsConstructor
public enum LogStatus {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 失败
     */
    FAIL(0);

    private final int value;
}
