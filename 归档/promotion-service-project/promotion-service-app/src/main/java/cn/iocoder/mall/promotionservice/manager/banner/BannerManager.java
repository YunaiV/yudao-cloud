package cn.iocoder.mall.promotionservice.manager.banner;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.*;
import cn.iocoder.mall.promotionservice.service.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

/**
 * Banner Manager
 */
@Service
@Valid
public class BannerManager {

    @Autowired
    private BannerService bannerService;

    public Integer createBanner(BannerCreateReqDTO createDTO) {
        return bannerService.createBanner(createDTO);
    }

    public void updateBanner(BannerUpdateReqDTO updateDTO) {
        bannerService.updateBanner(updateDTO);
    }

    public void deleteBanner(Integer bannerId) {
        bannerService.deleteBanner(bannerId);
    }

    public List<BannerRespDTO> listBanners(BannerListReqDTO listDTO) {
        return bannerService.listBanners(listDTO);
    }

    public PageResult<BannerRespDTO> pageBanner(BannerPageReqDTO pageDTO) {
        return bannerService.pageBanner(pageDTO);
    }

}
