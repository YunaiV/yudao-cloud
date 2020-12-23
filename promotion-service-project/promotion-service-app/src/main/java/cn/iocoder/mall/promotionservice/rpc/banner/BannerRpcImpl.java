package cn.iocoder.mall.promotionservice.rpc.banner;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.banner.BannerRpc;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.*;
import cn.iocoder.mall.promotionservice.manager.banner.BannerManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@DubboService
public class BannerRpcImpl implements BannerRpc {

    @Autowired
    private BannerManager bannerManager;

    @Override
    public CommonResult<Integer> createBanner(BannerCreateReqDTO createDTO) {
        return success(bannerManager.createBanner(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateBanner(BannerUpdateReqDTO updateDTO) {
        bannerManager.updateBanner(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteBanner(Integer bannerId) {
        bannerManager.deleteBanner(bannerId);
        return success(true);
    }

    @Override
    public CommonResult<List<BannerRespDTO>> listBanners(BannerListReqDTO listDTO) {
        return success(bannerManager.listBanners(listDTO));
    }

    @Override
    public CommonResult<PageResult<BannerRespDTO>> pageBanner(BannerPageReqDTO pageDTO) {
        return success(bannerManager.pageBanner(pageDTO));
    }

}
