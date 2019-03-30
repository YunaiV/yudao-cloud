package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminBannerPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersBannerVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    @Mappings({})
    AdminsBannerVO convert(BannerBO bannerBO);

    @Mappings({})
    CommonResult<AdminsBannerVO> convert2(CommonResult<BannerBO> result);

    @Mappings({})
    CommonResult<AdminBannerPageVO> convert(CommonResult<BannerPageBO> result);

    @Mappings({})
    List<UsersBannerVO> convertList(List<BannerBO> banners);

}