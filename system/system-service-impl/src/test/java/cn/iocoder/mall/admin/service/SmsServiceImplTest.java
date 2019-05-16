package cn.iocoder.mall.admin.service;

import cn.iocoder.mall.admin.SystemApplicationTest;
import cn.iocoder.mall.admin.service.SmsServiceImpl;
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
//        smsService.createSign("测试签名1");
        smsService.getSign("测试签名1");
    }
}
