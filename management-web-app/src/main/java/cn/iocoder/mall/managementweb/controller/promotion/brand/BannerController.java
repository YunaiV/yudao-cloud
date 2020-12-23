package cn.iocoder.mall.managementweb.controller.promotion.brand;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerPageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerRespVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerUpdateReqVO;
import cn.iocoder.mall.managementweb.manager.promotion.brand.BannerManager;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Banner Controller
 */
@RestController
@RequestMapping("/promotion/banner")
@Api(tags = "Banner API")
@Validated
public class BannerController {

    @Autowired
    private BannerManager bannerManager;

    @PostMapping("/create")
    @ApiOperation("创建 Banner")
    @RequiresPermissions("promotion:banner:create")
    public CommonResult<Integer> createBanner(@Valid BannerCreateReqVO createVO) {
        return success(bannerManager.createBanner(createVO));
    }

    @PostMapping("/update")
    @ApiOperation("更新 Banner")
    @RequiresPermissions("promotion:banner:update")
    public CommonResult<Boolean> updateBanner(@Valid BannerUpdateReqVO updateVO) {
        bannerManager.updateBanner(updateVO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除 Banner")
    @ApiImplicitParam(name = "bannerId", value = " Banner 编号", required = true)
    @RequiresPermissions("promotion:banner:delete")
    public CommonResult<Boolean> deleteBanner(@RequestParam("bannerId") Integer bannerId) {
        bannerManager.deleteBanner(bannerId);
        return success(true);
    }

    @GetMapping("/page")
    @ApiOperation("获得 Banner 分页")
    @RequiresPermissions("promotion:banner:page")
    public CommonResult<PageResult<BannerRespVO>> pageBanner(BannerPageReqVO pageVO) {
        return success(bannerManager.pageBanner(pageVO));
    }

}
