package cn.iocoder.yudao.module.infra.api.sse;

import cn.hutool.core.util.ReflectUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.sse.core.sender.SseMessageSender;
import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import cn.iocoder.yudao.module.infra.api.sse.dto.SseSendReqDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * {@link SseSenderApiImpl} 的单元测试
 *
 * @author HUIHUI
 */
public class SseSenderApiImplTest extends BaseMockitoUnitTest {

    @InjectMocks
    private SseSenderApiImpl sseSenderApi;

    @Mock
    private SseMessageSender sseMessageSender;

    @Test
    public void testSend_senderAbsent_skips() {
        // mock SSE 关闭后，可选的发送器未注入
        ReflectUtil.setFieldValue(sseSenderApi, "sseMessageSender", null);

        // 调用
        CommonResult<Boolean> result = sseSenderApi.send(new SseSendReqDTO().setUserType(1).setUserId(2L)
                .setMessageType("notice").setMessageContent("content"));
        // 断言
        assertTrue(result.getData());
        verifyNoInteractions(sseMessageSender);
    }

    @Test
    public void testSend_connectionId() {
        // 调用：connectionId 优先，忽略 userType、userId
        sseSenderApi.send(new SseSendReqDTO().setConnectionId("sse:abc").setUserType(1).setUserId(2L)
                .setMessageType("notice").setMessageContent("content"));
        // 断言
        verify(sseMessageSender).send("sse:abc", "notice", "content");
        verifyNoMoreInteractions(sseMessageSender);
    }

    @Test
    public void testSend_user() {
        // 调用
        sseSenderApi.send(new SseSendReqDTO().setUserType(1).setUserId(2L)
                .setMessageType("notice").setMessageContent("content"));
        // 断言
        verify(sseMessageSender).send(1, 2L, "notice", "content");
        verifyNoMoreInteractions(sseMessageSender);
    }

    @Test
    public void testSend_userType() {
        // 调用
        sseSenderApi.send(new SseSendReqDTO().setUserType(1)
                .setMessageType("notice").setMessageContent("content"));
        // 断言
        verify(sseMessageSender).send(1, "notice", "content");
        verifyNoMoreInteractions(sseMessageSender);
    }

    @Test
    public void testSend_noTarget_skips() {
        // 调用：未指定任何接收目标
        CommonResult<Boolean> result = sseSenderApi.send(new SseSendReqDTO()
                .setMessageType("notice").setMessageContent("content"));
        // 断言
        assertTrue(result.getData());
        verifyNoInteractions(sseMessageSender);
    }

}
