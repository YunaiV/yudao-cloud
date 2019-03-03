package cn.iocoder.mall.product.convert;

import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    @Mappings({})
    ProductSpuBO convert(ProductSpuDO spu);

}