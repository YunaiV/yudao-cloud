package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.application.vo.BannerPageVO;
import cn.iocoder.mall.promotion.application.vo.BannerVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    @Mappings({})
    BannerVO convert(BannerBO bannerBO);

    @Mappings({})
    CommonResult<BannerVO> convert2(CommonResult<BannerBO> result);

    @Mappings({})
    CommonResult<BannerPageVO> convert(CommonResult<BannerPageBO> result);

}