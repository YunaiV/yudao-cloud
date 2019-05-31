package cn.iocoder.mall.product.convert;

import cn.iocoder.mall.product.api.bo.ProductBrandBO;
import cn.iocoder.mall.product.api.dto.ProductBrandAddDTO;
import cn.iocoder.mall.product.api.dto.ProductBrandUpdateDTO;
import cn.iocoder.mall.product.dataobject.ProductBrandDO;
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