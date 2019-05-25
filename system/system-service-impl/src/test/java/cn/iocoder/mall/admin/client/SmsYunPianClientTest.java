package cn.iocoder.mall.admin.client;

import cn.iocoder.mall.admin.SystemApplicationTest;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.common.collect.ImmutableMap;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * 短信 sms client test
 *
 * @author Sin
 * @time 2019/5/25 12:46 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemApplicationTest.class)
public class SmsYunPianClientTest {

    @Autowired
    private SmsYunPianClient smsYunPianClient;

    private String sign = null;

    @Before
    public void setup() {
        sign = "悦跑运动";
    }

    @Test
    public void sendMobileTest() {
        String mobile = "13302926050";
        String template = "您的验证码是#code#，打死也不告诉别人哦。";
        smsYunPianClient.singleSend(mobile, sign, template, ImmutableMap.of("code", "1111"));
    }

    @Test
    public void batchSendTest() {
        String mobile = "13302926050";
        String template = "您的验证码是#code#，打死也不告诉别人哦。";
        smsYunPianClient.batchSend(Lists.newArrayList(mobile), sign, template, ImmutableMap.of("code", "2222"));
    }
}
