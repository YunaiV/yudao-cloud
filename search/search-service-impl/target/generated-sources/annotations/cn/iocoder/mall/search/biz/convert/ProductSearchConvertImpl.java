package cn.iocoder.mall.search.biz.convert;

import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.search.api.bo.ProductBO;
import cn.iocoder.mall.search.biz.dataobject.ESProductDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:12+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductSearchConvertImpl implements ProductSearchConvert {

    @Override
    public ESProductDO convert(ProductSpuDetailBO spu) {
        if ( spu == null ) {
            return null;
        }

        ESProductDO eSProductDO = new ESProductDO();

        eSProductDO.setId( spu.getId() );
        eSProductDO.setName( spu.getName() );
        eSProductDO.setSellPoint( spu.getSellPoint() );
        eSProductDO.setDescription( spu.getDescription() );
        eSProductDO.setCid( spu.getCid() );
        eSProductDO.setCategoryName( spu.getCategoryName() );
        List<String> list = spu.getPicUrls();
        if ( list != null ) {
            eSProductDO.setPicUrls( new ArrayList<String>( list ) );
        }
        eSProductDO.setVisible( spu.getVisible() );
        eSProductDO.setSort( spu.getSort() );

        return eSProductDO;
    }

    @Override
    public List<ProductBO> convert(List<ESProductDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductBO> list1 = new ArrayList<ProductBO>( list.size() );
        for ( ESProductDO eSProductDO : list ) {
            list1.add( eSProductDOToProductBO( eSProductDO ) );
        }

        return list1;
    }

    protected ProductBO eSProductDOToProductBO(ESProductDO eSProductDO) {
        if ( eSProductDO == null ) {
            return null;
        }

        ProductBO productBO = new ProductBO();

        productBO.setId( eSProductDO.getId() );
        productBO.setName( eSProductDO.getName() );
        productBO.setSellPoint( eSProductDO.getSellPoint() );
        productBO.setDescription( eSProductDO.getDescription() );
        productBO.setCid( eSProductDO.getCid() );
        productBO.setCategoryName( eSProductDO.getCategoryName() );
        List<String> list = eSProductDO.getPicUrls();
        if ( list != null ) {
            productBO.setPicUrls( new ArrayList<String>( list ) );
        }
        productBO.setVisible( eSProductDO.getVisible() );
        productBO.setSort( eSProductDO.getSort() );
        productBO.setOriginalPrice( eSProductDO.getOriginalPrice() );
        productBO.setBuyPrice( eSProductDO.getBuyPrice() );
        productBO.setQuantity( eSProductDO.getQuantity() );
        productBO.setPromotionActivityId( eSProductDO.getPromotionActivityId() );
        productBO.setPromotionActivityTitle( eSProductDO.getPromotionActivityTitle() );
        productBO.setPromotionActivityType( eSProductDO.getPromotionActivityType() );

        return productBO;
    }
}
