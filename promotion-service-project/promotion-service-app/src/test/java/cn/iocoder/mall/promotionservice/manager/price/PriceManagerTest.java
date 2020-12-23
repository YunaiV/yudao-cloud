package cn.iocoder.mall.promotionservice.manager.price;

import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PriceManagerTest {

    @Autowired
    private PriceManager priceManager;

    @Test
    public void testCalcProductPrice() {
        PriceProductCalcReqDTO calcReqDTO = new PriceProductCalcReqDTO();
        PriceProductCalcReqDTO.Item item01 = new PriceProductCalcReqDTO.Item(33, 2, true); // 满足满减送的商品
        PriceProductCalcReqDTO.Item item02 = new PriceProductCalcReqDTO.Item(34, 2, true); // 满足限时折扣的商品
        calcReqDTO.setItems(Arrays.asList(item01, item02));
        PriceProductCalcRespDTO calcRespDTO = priceManager.calcProductPrice(calcReqDTO);
        System.out.println(calcRespDTO);
    }

}
