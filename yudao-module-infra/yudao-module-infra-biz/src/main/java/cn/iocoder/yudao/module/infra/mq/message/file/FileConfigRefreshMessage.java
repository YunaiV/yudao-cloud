package cn.iocoder.yudao.module.infra.mq.message.file;

import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 文件配置数据刷新 Message
 */
@Data
public class FileConfigRefreshMessage extends RemoteApplicationEvent {

    public FileConfigRefreshMessage() {
    }

    public FileConfigRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }

}
