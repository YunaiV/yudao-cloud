package cn.iocoder.mall.system.biz.log.operation.event;

import cn.iocoder.mall.system.biz.log.operation.model.dto.OperationLogDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author
 * 系统日志事件
 */
@Getter
@AllArgsConstructor
public class OperationLogEvent {
	private final OperationLogDTO operationLogDTO;
}
