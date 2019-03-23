package cn.iocoder.mall.order.service;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.OrderApplicationTest;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.OrderBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import cn.iocoder.mall.order.dao.OrderMapper;
import cn.iocoder.mall.order.dataobject.OrderDO;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@Transactional
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void createOrderTest() {

        Integer userId = 1;
        OrderCreateItemDTO orderCreateItemDTO
                = new OrderCreateItemDTO()
                .setSkuId(1)
                .setQuantity(1);

        CommonResult<OrderBO> result = orderService.createOrder(
                userId,
                new OrderCreateDTO()
                .setRemark("")
                .setName("张三")
                .setMobile("13301926050")
                .setAddress("深圳市福田区")
                .setAreaNo("1000100")
                .setOrderItems(Arrays.asList(orderCreateItemDTO)));

        OrderDO orderDO = orderMapper.selectById(result.getData().getId());
        Assert.assertNotNull("创建的订单不存在!", orderDO);
    }
}
