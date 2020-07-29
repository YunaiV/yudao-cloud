package cn.iocoder.mall.promotionservice.convert.recommend;

import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendRespDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.recommend.ProductRecommendDO;
import cn.iocoder.mall.promotionservice.service.recommend.bo.ProductRecommendAddBO;
import cn.iocoder.mall.promotionservice.service.recommend.bo.ProductRecommendBO;
import cn.iocoder.mall.promotionservice.service.recommend.bo.ProductRecommendUpdateBO;
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
    List<ProductRecommendRespDTO> convertToDTO(List<ProductRecommendDO> recommendList);

    @Mappings({})
    ProductRecommendDO convert(ProductRecommendAddBO recommendAddDTO);

    @Mappings({})
    ProductRecommendDO convert(ProductRecommendUpdateBO recommendUpdateDTO);

}
