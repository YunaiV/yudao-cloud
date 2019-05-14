package cn.iocoder.mall.order.biz.service;

import cn.iocoder.mall.order.biz.OrderApplicationTest;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.biz.dao.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单 service test
 *
 * @author Sin
 * @time 2019-03-17 10:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplicationTest.class)
@Transactional
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void createOrderTest() {
//
//        Integer userId = 1;
//        OrderCreateItemDTO orderCreateItemDTO
//                = new OrderCreateItemDTO()
//                .setSkuId(1)
//                .setQuantity(1);
//
//        CommonResult<OrderCreateBO> result = orderService.createOrder(
//                userId,
//                new OrderCreateDTO()
//                .setRemark("")
//                .setName("张三")
//                .setMobile("13301926050")
//                .setAddress("深圳市福田区")
//                .setAreaNo("1000100")
//                .setOrderItems(Arrays.asList(orderCreateItemDTO)));
//
//        OrderDO orderDO = orderMapper.selectById(result.getData().getId());
//        Assert.assertNotNull("创建的订单不存在!", orderDO);
    }
}
