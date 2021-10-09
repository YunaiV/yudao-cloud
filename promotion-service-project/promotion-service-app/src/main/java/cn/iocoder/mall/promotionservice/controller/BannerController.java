package cn.iocoder.mall.promotionservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.*;
import cn.iocoder.mall.promotionservice.manager.banner.BannerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/promotion/banner")
public class BannerController {

    @Autowired
    private BannerManager bannerManager;

    @PostMapping("createBanner")
    public CommonResult<Integer> createBanner(@RequestBody BannerCreateReqDTO createDTO) {
        return success(bannerManager.createBanner(createDTO));
    }

    @PostMapping("updateBanner")
    public CommonResult<Boolean> updateBanner(@RequestBody BannerUpdateReqDTO updateDTO) {
        bannerManager.updateBanner(updateDTO);
        return success(true);
    }

    @PostMapping("deleteBanner")
    public CommonResult<Boolean> deleteBanner(@RequestBody Integer bannerId) {
        bannerManager.deleteBanner(bannerId);
        return success(true);
    }

    @PostMapping("listBanners")
    public CommonResult<List<BannerRespDTO>> listBanners(@RequestBody BannerListReqDTO listDTO) {
        return success(bannerManager.listBanners(listDTO));
    }

    @PostMapping("pageBanner")
    public CommonResult<PageResult<BannerRespDTO>> pageBanner(@RequestBody BannerPageReqDTO pageDTO) {
        return success(bannerManager.pageBanner(pageDTO));
    }
}
