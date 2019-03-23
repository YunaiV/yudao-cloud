package cn.iocoder.mall.order.service;

import cn.iocoder.mall.order.OrderApplicationTest;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 订单 service test
 *
 * @author Sin
 * @time 2019-03-17 10:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplicationTest.class)
//@Transactional
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private Environment environment;

    @Test
    public void createOrderTest() {
        OrderCreateItemDTO orderCreateItemDTO
                = new OrderCreateItemDTO()
                .setSkuId(1)
                .setQuantity(1);

        orderService.createOrder(
                new OrderCreateDTO()
                .setRemark("")
                .setName("张三")
                .setMobile("13301926050")
                .setAddress("深圳市福田区")
                .setAreaNo("1000100")
                .setOrderItems(Arrays.asList(orderCreateItemDTO)));
    }
}
