package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendAddDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendUpdateDTO;
import cn.iocoder.mall.promotion.biz.dataobject.ProductRecommendDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductRecommendConvert {

    ProductRecommendConvert INSTANCE = Mappers.getMapper(ProductRecommendConvert.class);

    @Mappings({})
    ProductRecommendBO convertToBO(ProductRecommendDO recommend);

    @Mappings({})
    List<ProductRecommendBO> convertToBO(List<ProductRecommendDO> recommendList);

    @Mappings({})
    ProductRecommendDO convert(ProductRecommendAddDTO recommendAddDTO);

    @Mappings({})
    ProductRecommendDO convert(ProductRecommendUpdateDTO recommendUpdateDTO);

}
