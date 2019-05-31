package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO.Item;
import cn.iocoder.mall.order.api.bo.CartItemBO;
import cn.iocoder.mall.order.biz.dataobject.CartItemDO;
import cn.iocoder.mall.product.api.bo.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:07+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class CartConvertImpl implements CartConvert {

    @Override
    public Item convert(ProductSkuDetailBO sku) {
        if ( sku == null ) {
            return null;
        }

        Item item = new Item();

        item.setId( sku.getId() );
        item.setSpu( sku.getSpu() );
        item.setPicURL( sku.getPicURL() );
        List<ProductAttrAndValuePairBO> list = sku.getAttrs();
        if ( list != null ) {
            item.setAttrs( new ArrayList<ProductAttrAndValuePairBO>( list ) );
        }
        item.setPrice( sku.getPrice() );
        item.setQuantity( sku.getQuantity() );

        return item;
    }

    @Override
    public List<CartItemBO> convert(List<CartItemDO> items) {
        if ( items == null ) {
            return null;
        }

        List<CartItemBO> list = new ArrayList<CartItemBO>( items.size() );
        for ( CartItemDO cartItemDO : items ) {
            list.add( cartItemDOToCartItemBO( cartItemDO ) );
        }

        return list;
    }

    protected CartItemBO cartItemDOToCartItemBO(CartItemDO cartItemDO) {
        if ( cartItemDO == null ) {
            return null;
        }

        CartItemBO cartItemBO = new CartItemBO();

        cartItemBO.setId( cartItemDO.getId() );
        cartItemBO.setStatus( cartItemDO.getStatus() );
        cartItemBO.setSelected( cartItemDO.getSelected() );
        cartItemBO.setUserId( cartItemDO.getUserId() );
        cartItemBO.setSpuId( cartItemDO.getSpuId() );
        cartItemBO.setSkuId( cartItemDO.getSkuId() );
        cartItemBO.setQuantity( cartItemDO.getQuantity() );
        cartItemBO.setOrderId( cartItemDO.getOrderId() );
        cartItemBO.setOrderCreateTime( cartItemDO.getOrderCreateTime() );
        cartItemBO.setCreateTime( cartItemDO.getCreateTime() );

        return cartItemBO;
    }
}
