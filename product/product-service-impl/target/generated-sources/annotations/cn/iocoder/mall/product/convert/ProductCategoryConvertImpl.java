package cn.iocoder.mall.product.convert;

import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.api.dto.ProductCategoryAddDTO;
import cn.iocoder.mall.product.api.dto.ProductCategoryUpdateDTO;
import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:38:55+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductCategoryConvertImpl implements ProductCategoryConvert {

    @Override
    public ProductCategoryBO convertToBO(ProductCategoryDO category) {
        if ( category == null ) {
            return null;
        }

        ProductCategoryBO productCategoryBO = new ProductCategoryBO();

        productCategoryBO.setId( category.getId() );
        productCategoryBO.setPid( category.getPid() );
        productCategoryBO.setName( category.getName() );
        productCategoryBO.setDescription( category.getDescription() );
        productCategoryBO.setPicUrl( category.getPicUrl() );
        productCategoryBO.setSort( category.getSort() );
        productCategoryBO.setStatus( category.getStatus() );
        productCategoryBO.setCreateTime( category.getCreateTime() );

        return productCategoryBO;
    }

    @Override
    public List<ProductCategoryBO> convertToBO(List<ProductCategoryDO> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<ProductCategoryBO> list = new ArrayList<ProductCategoryBO>( categoryList.size() );
        for ( ProductCategoryDO productCategoryDO : categoryList ) {
            list.add( convertToBO( productCategoryDO ) );
        }

        return list;
    }

    @Override
    public ProductCategoryDO convert(ProductCategoryAddDTO productCategoryAddDTO) {
        if ( productCategoryAddDTO == null ) {
            return null;
        }

        ProductCategoryDO productCategoryDO = new ProductCategoryDO();

        productCategoryDO.setPid( productCategoryAddDTO.getPid() );
        productCategoryDO.setName( productCategoryAddDTO.getName() );
        productCategoryDO.setDescription( productCategoryAddDTO.getDescription() );
        productCategoryDO.setPicUrl( productCategoryAddDTO.getPicUrl() );
        productCategoryDO.setSort( productCategoryAddDTO.getSort() );

        return productCategoryDO;
    }

    @Override
    public ProductCategoryDO convert(ProductCategoryUpdateDTO productCategoryUpdateDTO) {
        if ( productCategoryUpdateDTO == null ) {
            return null;
        }

        ProductCategoryDO productCategoryDO = new ProductCategoryDO();

        productCategoryDO.setId( productCategoryUpdateDTO.getId() );
        productCategoryDO.setPid( productCategoryUpdateDTO.getPid() );
        productCategoryDO.setName( productCategoryUpdateDTO.getName() );
        productCategoryDO.setDescription( productCategoryUpdateDTO.getDescription() );
        productCategoryDO.setPicUrl( productCategoryUpdateDTO.getPicUrl() );
        productCategoryDO.setSort( productCategoryUpdateDTO.getSort() );

        return productCategoryDO;
    }
}
