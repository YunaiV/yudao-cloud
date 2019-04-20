package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.bo.CartItemBO;
import cn.iocoder.mall.order.api.dto.*;
import cn.iocoder.mall.order.application.po.admin.OrderItemUpdatePO;
import cn.iocoder.mall.order.application.po.admin.OrderLogisticsPO;
import cn.iocoder.mall.order.application.po.admin.OrderPageQueryPO;
import cn.iocoder.mall.order.application.po.user.OrderCreatePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * application 订单 convert
 *
 * TODO 这种方式 文件名不能一样哈!
 *
 * @author Sin
 * @time 2019-03-24 10:45
 */
@Mapper
public interface OrderConvertAPP {

    OrderConvertAPP INSTANCE = Mappers.getMapper(OrderConvertAPP.class);

    @Mappings({})
    OrderQueryDTO convert(OrderPageQueryPO orderPageQueryVO);

    @Mappings({})
    OrderLogisticsUpdateDTO convert(OrderLogisticsPO orderLogisticsVO);

    @Mappings({})
    OrderItemUpdateDTO convert(OrderItemUpdatePO orderItemUpdateVO);

    @Mappings({})
    OrderCreateDTO convert(OrderCreatePO orderCreatePO);

    @Mappings({})
    List<OrderCreateItemDTO> convert(List<CartItemBO> cartItems);

    default OrderCreateDTO createOrderCreateDTO(Integer userId, Integer userAddressId, String remark, String ip,
                                                List<CartItemBO> cartItems, Integer couponCardId) {
        return new OrderCreateDTO()
                .setUserId(userId)
                .setUserAddressId(userAddressId)
                .setRemark(remark)
                .setIp(ip)
                .setOrderItems(this.convert(cartItems))
                .setCouponCardId(couponCardId);
    }

}
