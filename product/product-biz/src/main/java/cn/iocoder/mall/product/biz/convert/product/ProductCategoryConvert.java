package cn.iocoder.mall.product.biz.convert.product;

import cn.iocoder.mall.product.biz.bo.product.ProductCategoryBO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dto.product.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductCategoryUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    @Mappings({})
    ProductCategoryBO convertToBO(ProductCategoryDO category);

    @Mappings({})
    List<ProductCategoryBO> convertToBO(List<ProductCategoryDO> categoryList);

    @Mappings({})
    ProductCategoryDO convert(ProductCategoryAddDTO productCategoryAddDTO);

    @Mappings({})
    ProductCategoryDO convert(ProductCategoryUpdateDTO productCategoryUpdateDTO);

}