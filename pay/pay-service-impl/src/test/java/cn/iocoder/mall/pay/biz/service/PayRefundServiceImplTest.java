package cn.iocoder.mall.pay.biz.service;

import cn.iocoder.mall.pay.api.PayRefundService;
import cn.iocoder.mall.pay.api.dto.refund.PayRefundSubmitDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PayRefundServiceImplTest {

    @Autowired
    private PayRefundService payRefundService;

    @Test
    public void testSubmitRefund() {
        PayRefundSubmitDTO payRefundSubmitDTO = new PayRefundSubmitDTO()
                .setAppId("POd4RC6a")
                .setCreateIp("127.0.0.1")
                .setOrderId("13500000")
                .setOrderDescription("测试下退款")
                .setPrice(1);
        payRefundService.submitRefund(payRefundSubmitDTO);
    }

}
