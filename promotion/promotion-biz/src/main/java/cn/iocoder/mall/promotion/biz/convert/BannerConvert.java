package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.biz.bo.banner.BannerListBO;
import cn.iocoder.mall.promotion.biz.bo.banner.BannerListOnReleaseBO;
import cn.iocoder.mall.promotion.biz.dataobject.BannerDO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerAddDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    List<BannerListBO> convert(List<BannerDO> bannerDO);

    List<BannerListOnReleaseBO> convertToBO(List<BannerListBO> bannerList);

    BannerDO convert(BannerAddDTO bannerAddDTO);

    BannerDO convert(BannerUpdateDTO bannerUpdateDTO);

}