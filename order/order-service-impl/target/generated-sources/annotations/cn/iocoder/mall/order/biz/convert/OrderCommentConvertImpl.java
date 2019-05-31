package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentPageBO.OrderCommentItem;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:14:19+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderCommentConvertImpl implements OrderCommentConvert {

    @Override
    public OrderCommentDO convert(OrderCommentCreateDTO orderCommentCreateDTO) {
        if ( orderCommentCreateDTO == null ) {
            return null;
        }

        OrderCommentDO orderCommentDO = new OrderCommentDO();

        orderCommentDO.setOrderId( orderCommentCreateDTO.getOrderId() );
        orderCommentDO.setOrderNo( orderCommentCreateDTO.getOrderNo() );
        orderCommentDO.setProductSpuId( orderCommentCreateDTO.getProductSpuId() );
        orderCommentDO.setProductSpuName( orderCommentCreateDTO.getProductSpuName() );
        orderCommentDO.setProductSkuId( orderCommentCreateDTO.getProductSkuId() );
        orderCommentDO.setProductSkuAttrs( orderCommentCreateDTO.getProductSkuAttrs() );
        orderCommentDO.setProductSkuPrice( orderCommentCreateDTO.getProductSkuPrice() );
        orderCommentDO.setProductSkuPicUrl( orderCommentCreateDTO.getProductSkuPicUrl() );
        orderCommentDO.setUserId( orderCommentCreateDTO.getUserId() );
        orderCommentDO.setUserAvatar( orderCommentCreateDTO.getUserAvatar() );
        orderCommentDO.setUserNickName( orderCommentCreateDTO.getUserNickName() );
        orderCommentDO.setStar( orderCommentCreateDTO.getStar() );
        orderCommentDO.setProductDescriptionStar( orderCommentCreateDTO.getProductDescriptionStar() );
        orderCommentDO.setLogisticsStar( orderCommentCreateDTO.getLogisticsStar() );
        orderCommentDO.setMerchantStar( orderCommentCreateDTO.getMerchantStar() );
        orderCommentDO.setCommentContent( orderCommentCreateDTO.getCommentContent() );
        orderCommentDO.setCommentPics( orderCommentCreateDTO.getCommentPics() );

        return orderCommentDO;
    }

    @Override
    public OrderCommentCreateBO convert(OrderCommentDO orderCommentDO) {
        if ( orderCommentDO == null ) {
            return null;
        }

        OrderCommentCreateBO orderCommentCreateBO = new OrderCommentCreateBO();

        orderCommentCreateBO.setId( orderCommentDO.getId() );

        return orderCommentCreateBO;
    }

    @Override
    public List<OrderCommentItem> convert(List<OrderCommentDO> orderCommentDOList) {
        if ( orderCommentDOList == null ) {
            return null;
        }

        List<OrderCommentItem> list = new ArrayList<OrderCommentItem>( orderCommentDOList.size() );
        for ( OrderCommentDO orderCommentDO : orderCommentDOList ) {
            list.add( orderCommentDOToOrderCommentItem( orderCommentDO ) );
        }

        return list;
    }

    protected OrderCommentItem orderCommentDOToOrderCommentItem(OrderCommentDO orderCommentDO) {
        if ( orderCommentDO == null ) {
            return null;
        }

        OrderCommentItem orderCommentItem = new OrderCommentItem();

        orderCommentItem.setId( orderCommentDO.getId() );
        orderCommentItem.setUserAvatar( orderCommentDO.getUserAvatar() );
        orderCommentItem.setUserNickName( orderCommentDO.getUserNickName() );
        orderCommentItem.setStar( orderCommentDO.getStar() );
        orderCommentItem.setCommentContent( orderCommentDO.getCommentContent() );
        orderCommentItem.setCommentPics( orderCommentDO.getCommentPics() );
        orderCommentItem.setReplayCount( orderCommentDO.getReplayCount() );
        orderCommentItem.setCreateTime( orderCommentDO.getCreateTime() );

        return orderCommentItem;
    }
}
