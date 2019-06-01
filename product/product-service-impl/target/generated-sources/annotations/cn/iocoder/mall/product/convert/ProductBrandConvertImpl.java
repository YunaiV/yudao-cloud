package cn.iocoder.mall.product.convert;

import cn.iocoder.mall.product.api.bo.ProductBrandBO;
import cn.iocoder.mall.product.api.dto.ProductBrandAddDTO;
import cn.iocoder.mall.product.api.dto.ProductBrandUpdateDTO;
import cn.iocoder.mall.product.dataobject.ProductBrandDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:12:30+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductBrandConvertImpl implements ProductBrandConvert {

    @Override
    public List<ProductBrandBO> convert(List<ProductBrandDO> brands) {
        if ( brands == null ) {
            return null;
        }

        List<ProductBrandBO> list = new ArrayList<ProductBrandBO>( brands.size() );
        for ( ProductBrandDO productBrandDO : brands ) {
            list.add( convert( productBrandDO ) );
        }

        return list;
    }

    @Override
    public ProductBrandBO convert(ProductBrandDO brand) {
        if ( brand == null ) {
            return null;
        }

        ProductBrandBO productBrandBO = new ProductBrandBO();

        productBrandBO.setId( brand.getId() );
        productBrandBO.setName( brand.getName() );
        productBrandBO.setDescription( brand.getDescription() );
        productBrandBO.setPicUrl( brand.getPicUrl() );
        productBrandBO.setStatus( brand.getStatus() );

        return productBrandBO;
    }

    @Override
    public ProductBrandDO convert(ProductBrandUpdateDTO brand) {
        if ( brand == null ) {
            return null;
        }

        ProductBrandDO productBrandDO = new ProductBrandDO();

        productBrandDO.setId( brand.getId() );
        productBrandDO.setName( brand.getName() );
        productBrandDO.setDescription( brand.getDescription() );
        productBrandDO.setPicUrl( brand.getPicUrl() );
        productBrandDO.setStatus( brand.getStatus() );

        return productBrandDO;
    }

    @Override
    public ProductBrandDO convert(ProductBrandAddDTO brand) {
        if ( brand == null ) {
            return null;
        }

        ProductBrandDO productBrandDO = new ProductBrandDO();

        productBrandDO.setName( brand.getName() );
        productBrandDO.setDescription( brand.getDescription() );
        productBrandDO.setPicUrl( brand.getPicUrl() );
        productBrandDO.setStatus( brand.getStatus() );

        return productBrandDO;
    }
}
