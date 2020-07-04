package cn.iocoder.mall.promotion.api;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.api.dto.BannerAddDTO;
import cn.iocoder.mall.promotion.api.dto.BannerPageDTO;
import cn.iocoder.mall.promotion.api.dto.BannerUpdateDTO;

import java.util.List;

public interface BannerService {

    List<BannerBO> getBannerListByStatus(Integer status);

    BannerPageBO getBannerPage(BannerPageDTO bannerPageDTO);

    BannerBO addBanner(Integer adminId, BannerAddDTO bannerAddDTO);

    Boolean updateBanner(Integer adminId, BannerUpdateDTO bannerUpdateDTO);

    Boolean updateBannerStatus(Integer adminId, Integer bannerId,
                               @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    Boolean deleteBanner(Integer adminId, Integer bannerId);

}
