package cn.iocoder.mall.promotion.api.rpc.banner;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerRespDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerPageRespDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerAddReqDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerPageDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerUpdateReqDTO;

import java.util.List;

public interface BannerService {

    List<BannerRespDTO> getBannerListByStatus(Integer status);

    BannerPageRespDTO getBannerPage(BannerPageDTO bannerPageDTO);

    BannerRespDTO addBanner(Integer adminId, BannerAddReqDTO bannerAddDTO);

    Boolean updateBanner(Integer adminId, BannerUpdateReqDTO bannerUpdateDTO);

    Boolean updateBannerStatus(Integer adminId, Integer bannerId,
                               @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    Boolean deleteBanner(Integer adminId, Integer bannerId);

}
