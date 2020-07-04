package cn.iocoder.mall.promotion.application.controller.users;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.BannerService;
import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.application.convert.BannerConvert;
import cn.iocoder.mall.promotion.application.vo.users.UsersBannerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("users/banner")
@Api("Banner 模块")
public class UsersBannerController {

    @Reference(validation = "true", version = "${dubbo.provider.BannerService.version}")
    private BannerService bannerService;

    @GetMapping("/list")
    @ApiOperation("获得所有 Banner 列表")
    public CommonResult<List<UsersBannerVO>> list() {
        // 查询 Banner 列表
        List<BannerBO> result = bannerService.getBannerListByStatus(CommonStatusEnum.ENABLE.getValue());
        // 排序，按照 sort 升序
        result.sort(Comparator.comparing(BannerBO::getSort));
        // 返回
        return CommonResult.success(BannerConvert.USERS.convertList(result));
    }

}
