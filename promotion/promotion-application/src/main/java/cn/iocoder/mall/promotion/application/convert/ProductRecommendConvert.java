package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersProductRecommendVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductRecommendConvert {

    ProductRecommendConvert INSTANCE = Mappers.getMapper(ProductRecommendConvert.class);

    @Mappings({})
    AdminsProductRecommendVO convert(ProductRecommendBO bannerBO);

    @Mappings({})
    AdminsProductRecommendPageVO convert(ProductRecommendPageBO result);

    @Mappings({})
    UsersProductRecommendVO convert(ProductSpuBO productSpu);

    //    @Mappings({})
    //    List<UsersProductRecommendVO> convertList(List<ProductRecommendBO> banners);

}
