package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.bo.CartItemBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO.OrderItem;
import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderQueryDTO;
import cn.iocoder.mall.order.application.po.admin.OrderItemUpdatePO;
import cn.iocoder.mall.order.application.po.admin.OrderLogisticsPO;
import cn.iocoder.mall.order.application.po.admin.OrderPageQueryPO;
import cn.iocoder.mall.order.application.po.user.OrderCreatePO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:18+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderConvertAPPImpl implements OrderConvertAPP {

    @Override
    public OrderQueryDTO convert(OrderPageQueryPO orderPageQueryVO) {
        if ( orderPageQueryVO == null ) {
            return null;
        }

        OrderQueryDTO orderQueryDTO = new OrderQueryDTO();

        orderQueryDTO.setId( orderPageQueryVO.getId() );
        orderQueryDTO.setOrderNo( orderPageQueryVO.getOrderNo() );
        orderQueryDTO.setUserId( orderPageQueryVO.getUserId() );
        orderQueryDTO.setStartPaymentTime( orderPageQueryVO.getStartPaymentTime() );
        orderQueryDTO.setEndPaymentTime( orderPageQueryVO.getEndPaymentTime() );
        orderQueryDTO.setStartCreateTime( orderPageQueryVO.getStartCreateTime() );
        orderQueryDTO.setEndCreateTime( orderPageQueryVO.getEndCreateTime() );
        orderQueryDTO.setDeleted( orderPageQueryVO.getDeleted() );
        orderQueryDTO.setStatus( orderPageQueryVO.getStatus() );
        orderQueryDTO.setPageNo( orderPageQueryVO.getPageNo() );
        orderQueryDTO.setPageSize( orderPageQueryVO.getPageSize() );

        return orderQueryDTO;
    }

    @Override
    public OrderLogisticsUpdateDTO convert(OrderLogisticsPO orderLogisticsVO) {
        if ( orderLogisticsVO == null ) {
            return null;
        }

        OrderLogisticsUpdateDTO orderLogisticsUpdateDTO = new OrderLogisticsUpdateDTO();

        orderLogisticsUpdateDTO.setId( orderLogisticsVO.getId() );
        orderLogisticsUpdateDTO.setAreaNo( orderLogisticsVO.getAreaNo() );
        orderLogisticsUpdateDTO.setName( orderLogisticsVO.getName() );
        orderLogisticsUpdateDTO.setMobile( orderLogisticsVO.getMobile() );
        orderLogisticsUpdateDTO.setAddress( orderLogisticsVO.getAddress() );
        orderLogisticsUpdateDTO.setLogisticsNo( orderLogisticsVO.getLogisticsNo() );

        return orderLogisticsUpdateDTO;
    }

    @Override
    public OrderItemUpdateDTO convert(OrderItemUpdatePO orderItemUpdateVO) {
        if ( orderItemUpdateVO == null ) {
            return null;
        }

        OrderItemUpdateDTO orderItemUpdateDTO = new OrderItemUpdateDTO();

        orderItemUpdateDTO.setId( orderItemUpdateVO.getId() );
        orderItemUpdateDTO.setSkuId( orderItemUpdateVO.getSkuId() );
        orderItemUpdateDTO.setQuantity( orderItemUpdateVO.getQuantity() );
        orderItemUpdateDTO.setPrice( orderItemUpdateVO.getPrice() );

        return orderItemUpdateDTO;
    }

    @Override
    public OrderCreateDTO convert(OrderCreatePO orderCreatePO) {
        if ( orderCreatePO == null ) {
            return null;
        }

        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();

        orderCreateDTO.setUserAddressId( orderCreatePO.getUserAddressId() );
        orderCreateDTO.setCouponCardId( orderCreatePO.getCouponCardId() );
        orderCreateDTO.setRemark( orderCreatePO.getRemark() );
        orderCreateDTO.setOrderItems( orderItemListToOrderItemList( orderCreatePO.getOrderItems() ) );

        return orderCreateDTO;
    }

    @Override
    public List<OrderItem> convert(List<CartItemBO> cartItems) {
        if ( cartItems == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( cartItems.size() );
        for ( CartItemBO cartItemBO : cartItems ) {
            list.add( cartItemBOToOrderItem( cartItemBO ) );
        }

        return list;
    }

    protected OrderItem orderItemToOrderItem(cn.iocoder.mall.order.application.po.user.OrderCreatePO.OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItem orderItem1 = new OrderItem();

        orderItem1.setSkuId( orderItem.getSkuId() );
        orderItem1.setQuantity( orderItem.getQuantity() );

        return orderItem1;
    }

    protected List<OrderItem> orderItemListToOrderItemList(List<cn.iocoder.mall.order.application.po.user.OrderCreatePO.OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( cn.iocoder.mall.order.application.po.user.OrderCreatePO.OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItem( orderItem ) );
        }

        return list1;
    }

    protected OrderItem cartItemBOToOrderItem(CartItemBO cartItemBO) {
        if ( cartItemBO == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setSkuId( cartItemBO.getSkuId() );
        orderItem.setQuantity( cartItemBO.getQuantity() );

        return orderItem;
    }
}
