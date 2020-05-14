package cn.iocoder.mall.promotion.rest.controller.banner;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.biz.bo.banner.BannerListOnReleaseBO;
import cn.iocoder.mall.promotion.biz.service.banner.BannerService;
import cn.iocoder.mall.promotion.rest.convert.BannerConvert;
import cn.iocoder.mall.promotion.rest.response.banner.BannerListOnReleaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Banner(用户API)
 *
 * author: sin
 * time: 2020/5/14 15:27
 */
@RestController
@RequestMapping("/users/banner")
@Api(tags = "Banner(用户API)")
public class UsersBannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/listBannerOnRelease")
    @ApiOperation("获取-已发布的banner")
    public CommonResult<List<BannerListOnReleaseResponse>> listBannerOnRelease() {
        List<BannerListOnReleaseBO> releaseBOList = bannerService.listBannerOnRelease();
        return CommonResult.success(BannerConvert.INSTANCE.convertReleaseResponse(releaseBOList));
    }
}
