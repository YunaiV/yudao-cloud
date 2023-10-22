package cn.iocoder.yudao.module.member.mq.producer.user;

import cn.iocoder.yudao.framework.mq.core.bus.AbstractBusProducer;
import cn.iocoder.yudao.module.member.mq.message.user.UserCreateMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 会员用户 Producer
 *
 * @author owen
 */
@Slf4j
@Component
public class MemberUserProducer extends AbstractBusProducer {

    @Resource
    private StreamBridge streamBridge;

    // TODO 芋艿：后续要在细看下；
    /**
     * 发送 {@link UserCreateMessage} 消息
     *
     * @param userId 用户编号
     */
    public void sendUserCreateMessage(Long userId) {
        streamBridge.send("member-create-out-0",new UserCreateMessage().setUserId(userId));
    }

}
