package cn.iocoder.yudao.framework.websocket.core.sender.kafka;

import lombok.Data;

/**
 * Kafka 广播 WebSocket 的消息
 *
 * @author 芋道源码
 */
@Data
public class KafkaWebSocketMessage {

    /**
     * Session 编号
     */
    private String sessionId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 消息类型
     */
    private String messageType;
    /**
     * 消息内容
     */
    private String messageContent;

}
