package cn.iocoder.mall.tradeservice.service.order.impl;

import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeOrderServiceImplTest {

    @Autowired
    private TradeOrderServiceImpl tradeOrderService;

    @Test
    public void testCreateTradeOrder() {
        TradeOrderCreateReqDTO createReqDTO = new TradeOrderCreateReqDTO()
                .setUserId(243)
                .setIp("127.0.0.1")
                .setUserAddressId(19)
                .setCouponCardId(null)
                .setRemark("我是备注")
                .setOrderItems(Arrays.asList(
                       new TradeOrderCreateReqDTO.OrderItem().setSkuId(3).setQuantity(1),
                       new TradeOrderCreateReqDTO.OrderItem().setSkuId(4).setQuantity(2)
                ));
        tradeOrderService.createTradeOrder(createReqDTO);
    }

}
