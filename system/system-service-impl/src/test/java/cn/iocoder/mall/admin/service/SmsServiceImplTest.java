package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.mall.admin.SystemApplicationTest;
import cn.iocoder.mall.admin.api.SmsService;
import cn.iocoder.mall.admin.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.admin.api.constant.SmsPlatformEnum;
import cn.iocoder.mall.admin.api.constant.SmsTypeEnum;
import com.google.common.collect.ImmutableMap;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 短信 test
 *
 * @author Sin
 * @time 2019/5/16 10:52 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemApplicationTest.class)
public class SmsServiceImplTest {

    @Autowired
    private SmsService smsService;

    @Test
    public void createSignTest() {
        smsService.createSign("悦跑运动", SmsPlatformEnum.YunPian.getValue());

    }

    @Test
    public void getSignTest() {
        SmsSignBO smsSignBO =  smsService.getSign(3);
        Assert.assertNotNull("不能为空!", smsSignBO);
    }

    @Test
    public void updateSignTest() {
        String oldSign = "悦跑运动2";
        String newSign = "悦跑运动";
        smsService.updateSign(3, newSign, SmsPlatformEnum.YunPian.getValue());
        SmsSignBO smsSignBO =  smsService.getSign(3);
        Assert.assertTrue("更新不成功!", smsSignBO.getSign().equals(newSign));
    }


    @Test
    public void deletedSignTest() {
        smsService.deleteSign(3);
        Assertions.assertThrows(ServiceException.class, () -> {
            smsService.getSign(3);
        });
    }

    @Test
    public void createTemplateTest() {
        String template = "您的验证码是#code#，打死也不告诉别人哦。";
        smsService.createTemplate(3, template,
                SmsPlatformEnum.YunPian.getValue(),
                SmsTypeEnum.VERIFICATION_CODE.getValue());
    }

    @Test
    public void singleSendTest() {
        String mobile = "13302926050";
        Integer templateId = 5;
        smsService.singleSend(mobile, templateId, ImmutableMap.of("code", "8888"));
    }

    @Test
    public void batchSendTest() {
        String mobile = "13302926050";
        Integer templateId = 5;
        smsService.batchSend(Lists.newArrayList(mobile), templateId, ImmutableMap.of("code", "8888"));
    }
}
