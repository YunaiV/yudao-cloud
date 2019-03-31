package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductRecommendConvert {

    ProductRecommendConvert INSTANCE = Mappers.getMapper(ProductRecommendConvert.class);

    @Mappings({})
    AdminsProductRecommendVO convert(ProductRecommendBO bannerBO);

    @Mappings({})
    CommonResult<AdminsProductRecommendVO> convert2(CommonResult<ProductRecommendBO> result);

    @Mappings({})
    CommonResult<AdminsProductRecommendPageVO> convert(CommonResult<ProductRecommendPageBO> result);

//    @Mappings({})
//    List<UsersProductRecommendVO> convertList(List<ProductRecommendBO> banners);

}