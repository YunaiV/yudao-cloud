package cn.iocoder.yudao.module.prod.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode TASK_LIST_NOT_EXISTS = new ErrorCode(100000010, "任务列不存在");
    ErrorCode INSERT_POINT_NOT_EXISTS = new ErrorCode(100000020, "插入点不存在");
}
