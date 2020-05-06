package cn.iocoder.mall.product.biz.convert.product;

import cn.iocoder.mall.product.biz.bo.product.ProductBrandBO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductBrandDO;
import cn.iocoder.mall.product.biz.dto.product.ProductBrandAddDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductBrandUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    @Mappings({})
    List<ProductBrandBO> convert(List<ProductBrandDO> brands);

    @Mappings({})
    ProductBrandBO convert(ProductBrandDO brand);

    @Mappings({})
    ProductBrandDO convert(ProductBrandUpdateDTO brand);

    @Mappings({})
    ProductBrandDO convert(ProductBrandAddDTO brand);

}