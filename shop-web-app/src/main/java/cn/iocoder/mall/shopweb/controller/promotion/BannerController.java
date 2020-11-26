package cn.iocoder.mall.shopweb.controller.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.shopweb.controller.promotion.vo.brand.BannerRespVO;
import cn.iocoder.mall.shopweb.service.promotion.BannerManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/promotion/banner")
@Api(tags = "Banner API")
@Validated
public class BannerController {

    @Autowired
    private BannerManager bannerManager;

    @GetMapping("/list")
    @ApiOperation("获得所有 Banner 列表")
    public CommonResult<List<BannerRespVO>> listBanners() {
        return success(bannerManager.listBanners());
    }

}
