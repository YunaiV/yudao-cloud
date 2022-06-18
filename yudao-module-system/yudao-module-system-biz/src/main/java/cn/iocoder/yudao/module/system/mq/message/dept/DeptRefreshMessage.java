package cn.iocoder.yudao.module.system.mq.message.dept;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 部门数据刷新 Message
 *
 * @author 芋道源码
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptRefreshMessage extends RemoteApplicationEvent {

    public DeptRefreshMessage() {
    }

    public DeptRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }

}
