package cn.iocoder.mall.shopweb.convert.promotion;

import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductRecommendConvert {

    ProductRecommendConvert INSTANCE = Mappers.getMapper(ProductRecommendConvert.class);

    ProductSpuRespVO convert(ProductSpuRespDTO bean);

}
