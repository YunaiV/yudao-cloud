package cn.iocoder.mall.admin.client;

import cn.iocoder.mall.admin.SystemApplicationTest;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 阿里云 短信 test
 *
 * @author Sin
 * @time 2019/5/26 10:08 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemApplicationTest.class)
public class SmsAliYunClientTest {

    @Autowired
    private SmsAliYunClient smsAliYunClient;

    @Test
    public void singleSendTest() {
        String sign = "阿里云短信测试专用";
        String mobile = "13302926050";
        String templateCode = "SMS_137110043";
        String template = "验证码#code#，您正在进行身份验证，打死不要告诉别人哦！";
        smsAliYunClient.singleSend(mobile, sign, templateCode,
                template, ImmutableMap.of("code", "8888"));
    }
}
