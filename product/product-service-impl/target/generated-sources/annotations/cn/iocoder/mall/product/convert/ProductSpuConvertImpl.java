package cn.iocoder.mall.product.convert;

import cn.iocoder.mall.product.api.bo.ProductSkuBO;
import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO.Spu;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO.Sku;
import cn.iocoder.mall.product.api.dto.ProductSkuAddOrUpdateDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuUpdateDTO;
import cn.iocoder.mall.product.dataobject.ProductSkuDO;
import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:38:56+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductSpuConvertImpl implements ProductSpuConvert {

    @Override
    public ProductSpuBO convert(ProductSpuDO spu) {
        if ( spu == null ) {
            return null;
        }

        ProductSpuBO productSpuBO = new ProductSpuBO();

        productSpuBO.setPicUrls( translatePicUrlsFromString( spu.getPicUrls() ) );
        productSpuBO.setId( spu.getId() );
        productSpuBO.setName( spu.getName() );
        productSpuBO.setSellPoint( spu.getSellPoint() );
        productSpuBO.setDescription( spu.getDescription() );
        productSpuBO.setCid( spu.getCid() );
        productSpuBO.setVisible( spu.getVisible() );
        productSpuBO.setSort( spu.getSort() );
        productSpuBO.setPrice( spu.getPrice() );
        productSpuBO.setQuantity( spu.getQuantity() );

        return productSpuBO;
    }

    @Override
    public List<ProductSpuBO> convert(List<ProductSpuDO> spus) {
        if ( spus == null ) {
            return null;
        }

        List<ProductSpuBO> list = new ArrayList<ProductSpuBO>( spus.size() );
        for ( ProductSpuDO productSpuDO : spus ) {
            list.add( convert( productSpuDO ) );
        }

        return list;
    }

    @Override
    public ProductSpuDO convert(ProductSpuAddDTO productSpuAddDTO) {
        if ( productSpuAddDTO == null ) {
            return null;
        }

        ProductSpuDO productSpuDO = new ProductSpuDO();

        productSpuDO.setName( productSpuAddDTO.getName() );
        productSpuDO.setSellPoint( productSpuAddDTO.getSellPoint() );
        productSpuDO.setDescription( productSpuAddDTO.getDescription() );
        productSpuDO.setCid( productSpuAddDTO.getCid() );
        productSpuDO.setVisible( productSpuAddDTO.getVisible() );

        return productSpuDO;
    }

    @Override
    public ProductSkuDO convert(ProductSkuAddOrUpdateDTO productSkuAddDTO) {
        if ( productSkuAddDTO == null ) {
            return null;
        }

        ProductSkuDO productSkuDO = new ProductSkuDO();

        productSkuDO.setPrice( productSkuAddDTO.getPrice() );
        productSkuDO.setQuantity( productSkuAddDTO.getQuantity() );

        return productSkuDO;
    }

    @Override
    public ProductSpuDO convert(ProductSpuUpdateDTO productSpuUpdateDTO) {
        if ( productSpuUpdateDTO == null ) {
            return null;
        }

        ProductSpuDO productSpuDO = new ProductSpuDO();

        productSpuDO.setId( productSpuUpdateDTO.getId() );
        productSpuDO.setName( productSpuUpdateDTO.getName() );
        productSpuDO.setSellPoint( productSpuUpdateDTO.getSellPoint() );
        productSpuDO.setDescription( productSpuUpdateDTO.getDescription() );
        productSpuDO.setCid( productSpuUpdateDTO.getCid() );
        productSpuDO.setVisible( productSpuUpdateDTO.getVisible() );

        return productSpuDO;
    }

    @Override
    public ProductSpuDetailBO convert(ProductSpuBO spu) {
        if ( spu == null ) {
            return null;
        }

        ProductSpuDetailBO productSpuDetailBO = new ProductSpuDetailBO();

        productSpuDetailBO.setId( spu.getId() );
        productSpuDetailBO.setName( spu.getName() );
        productSpuDetailBO.setSellPoint( spu.getSellPoint() );
        productSpuDetailBO.setDescription( spu.getDescription() );
        productSpuDetailBO.setCid( spu.getCid() );
        List<String> list = spu.getPicUrls();
        if ( list != null ) {
            productSpuDetailBO.setPicUrls( new ArrayList<String>( list ) );
        }
        productSpuDetailBO.setVisible( spu.getVisible() );
        productSpuDetailBO.setSort( spu.getSort() );

        return productSpuDetailBO;
    }

    @Override
    public ProductSpuDetailBO convert2(ProductSpuDO spu) {
        if ( spu == null ) {
            return null;
        }

        ProductSpuDetailBO productSpuDetailBO = new ProductSpuDetailBO();

        productSpuDetailBO.setId( spu.getId() );
        productSpuDetailBO.setName( spu.getName() );
        productSpuDetailBO.setSellPoint( spu.getSellPoint() );
        productSpuDetailBO.setDescription( spu.getDescription() );
        productSpuDetailBO.setCid( spu.getCid() );
        productSpuDetailBO.setVisible( spu.getVisible() );
        productSpuDetailBO.setSort( spu.getSort() );

        return productSpuDetailBO;
    }

    @Override
    public Spu convert3(ProductSpuDO spu) {
        if ( spu == null ) {
            return null;
        }

        Spu spu1 = new Spu();

        spu1.setId( spu.getId() );
        spu1.setName( spu.getName() );
        spu1.setSellPoint( spu.getSellPoint() );
        spu1.setDescription( spu.getDescription() );
        spu1.setCid( spu.getCid() );
        spu1.setVisible( spu.getVisible() );
        spu1.setSort( spu.getSort() );

        return spu1;
    }

    @Override
    public Sku convert2(ProductSkuDO sku) {
        if ( sku == null ) {
            return null;
        }

        Sku sku1 = new Sku();

        sku1.setId( sku.getId() );
        sku1.setSpuId( sku.getSpuId() );
        sku1.setPrice( sku.getPrice() );
        sku1.setQuantity( sku.getQuantity() );

        return sku1;
    }

    @Override
    public ProductSkuDetailBO convert3(ProductSkuDO sku) {
        if ( sku == null ) {
            return null;
        }

        ProductSkuDetailBO productSkuDetailBO = new ProductSkuDetailBO();

        productSkuDetailBO.setId( sku.getId() );
        productSkuDetailBO.setPrice( sku.getPrice() );
        productSkuDetailBO.setQuantity( sku.getQuantity() );

        return productSkuDetailBO;
    }

    @Override
    public ProductSkuBO convert4(ProductSkuDO sku) {
        if ( sku == null ) {
            return null;
        }

        ProductSkuBO productSkuBO = new ProductSkuBO();

        productSkuBO.setId( sku.getId() );
        productSkuBO.setSpuId( sku.getSpuId() );
        productSkuBO.setStatus( sku.getStatus() );
        productSkuBO.setPrice( sku.getPrice() );
        productSkuBO.setQuantity( sku.getQuantity() );

        return productSkuBO;
    }
}
