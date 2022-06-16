package cn.iocoder.mall.payservice.service.transaction.impl;

import cn.iocoder.mall.payservice.enums.PayChannelEnum;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionSubmitReqDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayTransactionServiceImplTest {

    @Autowired
    private PayTransactionServiceImpl payTransactionService;

    @Test
    public void testSubmitPayTransaction() {
        payTransactionService.submitPayTransaction(new PayTransactionSubmitReqDTO()
            .setAppId("POd4RC6a")
            .setCreateIp("127.0.0.1")
            .setOrderId("239")
            .setPayChannel(PayChannelEnum.PINGXX.getId()));
    }

}
