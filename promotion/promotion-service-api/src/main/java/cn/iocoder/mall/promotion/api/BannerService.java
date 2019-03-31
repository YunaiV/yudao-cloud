package cn.iocoder.mall.promotion.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.api.dto.BannerAddDTO;
import cn.iocoder.mall.promotion.api.dto.BannerPageDTO;
import cn.iocoder.mall.promotion.api.dto.BannerUpdateDTO;

import java.util.List;

public interface BannerService {

    CommonResult<List<BannerBO>> getBannerListByStatus(Integer status);

    CommonResult<BannerPageBO> getBannerPage(BannerPageDTO bannerPageDTO);

    CommonResult<BannerBO> addBanner(Integer adminId, BannerAddDTO bannerAddDTO);

    CommonResult<Boolean> updateBanner(Integer adminId, BannerUpdateDTO bannerUpdateDTO);

    CommonResult<Boolean> updateBannerStatus(Integer adminId, Integer bannerId, Integer status);

    CommonResult<Boolean> deleteBanner(Integer adminId, Integer bannerId);

}