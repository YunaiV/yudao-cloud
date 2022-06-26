package cn.iocoder.mall.managementweb.convert.promotion;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.recommend.vo.ProductRecommendCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.recommend.vo.ProductRecommendDetailVO;
import cn.iocoder.mall.managementweb.controller.promotion.recommend.vo.ProductRecommendPageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.recommend.vo.ProductRecommendUpdateReqVO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendCreateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendRespDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductRecommendConvert {

    ProductRecommendConvert INSTANCE = Mappers.getMapper(ProductRecommendConvert.class);

    ProductRecommendCreateReqDTO convert(ProductRecommendCreateReqVO bean);

    ProductRecommendUpdateReqDTO convert(ProductRecommendUpdateReqVO bean);

    ProductRecommendPageReqDTO convert(ProductRecommendPageReqVO bean);

    PageResult<ProductRecommendDetailVO> convertPage(PageResult<ProductRecommendRespDTO> page);

    ProductRecommendDetailVO.Spu convert(ProductSpuRespDTO bean);

}
