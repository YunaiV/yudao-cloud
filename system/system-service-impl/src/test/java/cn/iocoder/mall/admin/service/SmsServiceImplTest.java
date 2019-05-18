package cn.iocoder.mall.admin.service;

import cn.iocoder.mall.admin.SystemApplicationTest;
import cn.iocoder.mall.admin.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.admin.api.bo.sms.SmsTemplateBO;
import org.junit.Assert;
import org.junit.Test;
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
    private SmsServiceImpl smsService;

    @Test
    public void createSignTest() {
        smsService.createSign("测试签名1");
//        smsService.createSign("悦跑会");
    }

    /**
     * 测试本地 git mail 修改后 2
     */
    @Test
    public void getSignTest() {
        SmsSignBO smsSignBO = smsService.getSign("悦跑会");
        Assert.assertNotNull(smsSignBO);
    }

    @Test
    public void updateSignTest() {
        smsService.updateSign("测试签名2", "测试签名3");
        SmsSignBO newSmsSignBO = smsService.getSign("测试签名3");
        Assert.assertNotNull(newSmsSignBO);
    }


    ///
    /// template

    @Test
    public void createTemplateTest() {
        smsService.createTemplate(1, "打死也不要告诉别人哦002 #code# ", 1);
    }

    @Test
    public void getTemplateTest() {
        SmsTemplateBO smsTemplateBO = smsService.getTemplate(3);
        Assert.assertNotNull(smsTemplateBO);
    }

    @Test
    public void updateTemplateTest() {
        smsService.updateTemplate(3, "打死也不要告诉别人哦444 #code# ", 1);
    }

    @Test
    public void deleteTemplateTest() {
        smsService.deleteTemplate(3);
    }
}
