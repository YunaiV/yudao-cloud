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
//        Properties properties = System.getProperties();
//        String url = properties.getProperty("spring.datasource.url");
//        System.err.println(url);

        String url = environment.getProperty("spring.datasource.url");
        System.err.println(url);


        OrderCreateItemDTO orderCreateItemDTO
                = new OrderCreateItemDTO()
                .setCommodityId("CID_001")
                .setQuantity(1);

        orderService.createOrder(new OrderCreateDTO()
                .setRemark("")
                .setReceiverMobile("13301926050")
                .setReceiverAddress("深圳市福田区")
                .setReceiverAreaNo("1000100")
                .setOrderItems(Arrays.asList(orderCreateItemDTO)));
    }
}
