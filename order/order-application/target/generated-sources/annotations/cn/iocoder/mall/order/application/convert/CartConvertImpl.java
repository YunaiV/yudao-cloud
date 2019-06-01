package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO;
import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO.Item;
import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO.ItemGroup;
import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;
import cn.iocoder.mall.order.application.vo.UsersCalcSkuPriceVO;
import cn.iocoder.mall.order.application.vo.UsersCartDetailVO;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.Fee;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.Sku;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.Spu;
import cn.iocoder.mall.product.api.bo.ProductAttrAndValuePairBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:18+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class CartConvertImpl implements CartConvert {

    @Override
    public UsersOrderConfirmCreateVO convert(CalcOrderPriceBO calcOrderPriceBO) {
        if ( calcOrderPriceBO == null ) {
            return null;
        }

        UsersOrderConfirmCreateVO usersOrderConfirmCreateVO = new UsersOrderConfirmCreateVO();

        usersOrderConfirmCreateVO.setItemGroups( itemGroupListToItemGroupList( calcOrderPriceBO.getItemGroups() ) );
        usersOrderConfirmCreateVO.setFee( feeToFee( calcOrderPriceBO.getFee() ) );
        usersOrderConfirmCreateVO.setCouponCardDiscountTotal( calcOrderPriceBO.getCouponCardDiscountTotal() );

        return usersOrderConfirmCreateVO;
    }

    @Override
    public UsersCartDetailVO convert2(CalcOrderPriceBO calcOrderPriceBO) {
        if ( calcOrderPriceBO == null ) {
            return null;
        }

        UsersCartDetailVO usersCartDetailVO = new UsersCartDetailVO();

        usersCartDetailVO.setItemGroups( itemGroupListToItemGroupList1( calcOrderPriceBO.getItemGroups() ) );
        usersCartDetailVO.setFee( feeToFee1( calcOrderPriceBO.getFee() ) );

        return usersCartDetailVO;
    }

    @Override
    public UsersCalcSkuPriceVO convert2(CalcSkuPriceBO calcSkuPriceBO) {
        if ( calcSkuPriceBO == null ) {
            return null;
        }

        UsersCalcSkuPriceVO usersCalcSkuPriceVO = new UsersCalcSkuPriceVO();

        usersCalcSkuPriceVO.setFullPrivilege( calcSkuPriceBO.getFullPrivilege() );
        usersCalcSkuPriceVO.setTimeLimitedDiscount( calcSkuPriceBO.getTimeLimitedDiscount() );
        usersCalcSkuPriceVO.setOriginalPrice( calcSkuPriceBO.getOriginalPrice() );
        usersCalcSkuPriceVO.setBuyPrice( calcSkuPriceBO.getBuyPrice() );

        return usersCalcSkuPriceVO;
    }

    protected Spu spuToSpu(cn.iocoder.mall.product.api.bo.ProductSkuDetailBO.Spu spu) {
        if ( spu == null ) {
            return null;
        }

        Spu spu1 = new Spu();

        spu1.setId( spu.getId() );
        spu1.setName( spu.getName() );
        spu1.setCid( spu.getCid() );
        List<String> list = spu.getPicUrls();
        if ( list != null ) {
            spu1.setPicUrls( new ArrayList<String>( list ) );
        }

        return spu1;
    }

    protected Sku itemToSku(Item item) {
        if ( item == null ) {
            return null;
        }

        Sku sku = new Sku();

        sku.setId( item.getId() );
        sku.setSpu( spuToSpu( item.getSpu() ) );
        sku.setPicURL( item.getPicURL() );
        List<ProductAttrAndValuePairBO> list = item.getAttrs();
        if ( list != null ) {
            sku.setAttrs( new ArrayList<ProductAttrAndValuePairBO>( list ) );
        }
        sku.setPrice( item.getPrice() );
        sku.setQuantity( item.getQuantity() );
        sku.setBuyQuantity( item.getBuyQuantity() );
        sku.setOriginPrice( item.getOriginPrice() );
        sku.setBuyPrice( item.getBuyPrice() );
        sku.setPresentPrice( item.getPresentPrice() );
        sku.setBuyTotal( item.getBuyTotal() );
        sku.setDiscountTotal( item.getDiscountTotal() );
        sku.setPresentTotal( item.getPresentTotal() );

        return sku;
    }

    protected List<Sku> itemListToSkuList(List<Item> list) {
        if ( list == null ) {
            return null;
        }

        List<Sku> list1 = new ArrayList<Sku>( list.size() );
        for ( Item item : list ) {
            list1.add( itemToSku( item ) );
        }

        return list1;
    }

    protected cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.ItemGroup itemGroupToItemGroup(ItemGroup itemGroup) {
        if ( itemGroup == null ) {
            return null;
        }

        cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.ItemGroup itemGroup1 = new cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.ItemGroup();

        itemGroup1.setActivity( itemGroup.getActivity() );
        itemGroup1.setItems( itemListToSkuList( itemGroup.getItems() ) );

        return itemGroup1;
    }

    protected List<cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.ItemGroup> itemGroupListToItemGroupList(List<ItemGroup> list) {
        if ( list == null ) {
            return null;
        }

        List<cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.ItemGroup> list1 = new ArrayList<cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO.ItemGroup>( list.size() );
        for ( ItemGroup itemGroup : list ) {
            list1.add( itemGroupToItemGroup( itemGroup ) );
        }

        return list1;
    }

    protected Fee feeToFee(cn.iocoder.mall.order.api.bo.CalcOrderPriceBO.Fee fee) {
        if ( fee == null ) {
            return null;
        }

        Fee fee1 = new Fee();

        fee1.setBuyTotal( fee.getBuyTotal() );
        fee1.setDiscountTotal( fee.getDiscountTotal() );
        fee1.setPostageTotal( fee.getPostageTotal() );
        fee1.setPresentTotal( fee.getPresentTotal() );

        return fee1;
    }

    protected cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Spu spuToSpu1(cn.iocoder.mall.product.api.bo.ProductSkuDetailBO.Spu spu) {
        if ( spu == null ) {
            return null;
        }

        cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Spu spu1 = new cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Spu();

        spu1.setId( spu.getId() );
        spu1.setName( spu.getName() );
        spu1.setCid( spu.getCid() );
        List<String> list = spu.getPicUrls();
        if ( list != null ) {
            spu1.setPicUrls( new ArrayList<String>( list ) );
        }

        return spu1;
    }

    protected cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Sku itemToSku1(Item item) {
        if ( item == null ) {
            return null;
        }

        cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Sku sku = new cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Sku();

        sku.setId( item.getId() );
        sku.setSpu( spuToSpu1( item.getSpu() ) );
        sku.setPicURL( item.getPicURL() );
        List<ProductAttrAndValuePairBO> list = item.getAttrs();
        if ( list != null ) {
            sku.setAttrs( new ArrayList<ProductAttrAndValuePairBO>( list ) );
        }
        sku.setPrice( item.getPrice() );
        sku.setQuantity( item.getQuantity() );
        sku.setBuyQuantity( item.getBuyQuantity() );
        sku.setSelected( item.getSelected() );
        sku.setActivity( item.getActivity() );
        sku.setOriginPrice( item.getOriginPrice() );
        sku.setBuyPrice( item.getBuyPrice() );
        sku.setPresentPrice( item.getPresentPrice() );
        sku.setBuyTotal( item.getBuyTotal() );
        sku.setDiscountTotal( item.getDiscountTotal() );
        sku.setPresentTotal( item.getPresentTotal() );

        return sku;
    }

    protected List<cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Sku> itemListToSkuList1(List<Item> list) {
        if ( list == null ) {
            return null;
        }

        List<cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Sku> list1 = new ArrayList<cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Sku>( list.size() );
        for ( Item item : list ) {
            list1.add( itemToSku1( item ) );
        }

        return list1;
    }

    protected cn.iocoder.mall.order.application.vo.UsersCartDetailVO.ItemGroup itemGroupToItemGroup1(ItemGroup itemGroup) {
        if ( itemGroup == null ) {
            return null;
        }

        cn.iocoder.mall.order.application.vo.UsersCartDetailVO.ItemGroup itemGroup1 = new cn.iocoder.mall.order.application.vo.UsersCartDetailVO.ItemGroup();

        itemGroup1.setActivity( itemGroup.getActivity() );
        itemGroup1.setActivityDiscountTotal( itemGroup.getActivityDiscountTotal() );
        itemGroup1.setItems( itemListToSkuList1( itemGroup.getItems() ) );

        return itemGroup1;
    }

    protected List<cn.iocoder.mall.order.application.vo.UsersCartDetailVO.ItemGroup> itemGroupListToItemGroupList1(List<ItemGroup> list) {
        if ( list == null ) {
            return null;
        }

        List<cn.iocoder.mall.order.application.vo.UsersCartDetailVO.ItemGroup> list1 = new ArrayList<cn.iocoder.mall.order.application.vo.UsersCartDetailVO.ItemGroup>( list.size() );
        for ( ItemGroup itemGroup : list ) {
            list1.add( itemGroupToItemGroup1( itemGroup ) );
        }

        return list1;
    }

    protected cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Fee feeToFee1(cn.iocoder.mall.order.api.bo.CalcOrderPriceBO.Fee fee) {
        if ( fee == null ) {
            return null;
        }

        cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Fee fee1 = new cn.iocoder.mall.order.application.vo.UsersCartDetailVO.Fee();

        fee1.setBuyTotal( fee.getBuyTotal() );
        fee1.setDiscountTotal( fee.getDiscountTotal() );
        fee1.setPostageTotal( fee.getPostageTotal() );
        fee1.setPresentTotal( fee.getPresentTotal() );

        return fee1;
    }
}
