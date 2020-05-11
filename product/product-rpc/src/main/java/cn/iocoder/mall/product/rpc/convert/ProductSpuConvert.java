package cn.iocoder.mall.product.rpc.convert;

import cn.iocoder.mall.product.biz.bo.product.ProductSpuDetailBO;
import cn.iocoder.mall.product.rpc.response.ProductSpuDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    @Mappings({})
    ProductSpuDetailResponse convertDetail(ProductSpuDetailBO productSpuDetail);
}
