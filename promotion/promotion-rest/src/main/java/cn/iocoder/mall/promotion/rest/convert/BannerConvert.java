package cn.iocoder.mall.promotion.rest.convert;

import cn.iocoder.mall.promotion.biz.bo.banner.BannerListBO;
import cn.iocoder.mall.promotion.biz.bo.banner.BannerListOnReleaseBO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerAddDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerUpdateDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerListDTO;
import cn.iocoder.mall.promotion.rest.request.banner.BannerAddRequest;
import cn.iocoder.mall.promotion.rest.request.banner.BannerListRequest;
import cn.iocoder.mall.promotion.rest.request.banner.BannerUpdateRequest;
import cn.iocoder.mall.promotion.rest.response.banner.BannerListResponse;
import cn.iocoder.mall.promotion.rest.response.banner.BannerListOnReleaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    BannerAddDTO convert(BannerAddRequest request);

    BannerUpdateDTO convert(BannerUpdateRequest request);

    BannerListDTO convert(BannerListRequest request);

    List<BannerListResponse> convert(List<BannerListBO> bannerListBO);

    List<BannerListOnReleaseResponse> convertReleaseResponse(List<BannerListOnReleaseBO> bannerListOnReleaseBOS);

}