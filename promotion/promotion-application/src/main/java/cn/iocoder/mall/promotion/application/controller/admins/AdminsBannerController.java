package cn.iocoder.mall.promotion.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import cn.iocoder.mall.promotion.api.BannerService;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.api.dto.BannerAddDTO;
import cn.iocoder.mall.promotion.api.dto.BannerPageDTO;
import cn.iocoder.mall.promotion.api.dto.BannerUpdateDTO;
import cn.iocoder.mall.promotion.application.convert.BannerConvert;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerVO;
import org.apache.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admins/banner")
@Api("Banner 模块")
public class AdminsBannerController {

    @Reference(validation = "true")
    private BannerService bannerService;

    @GetMapping("/page")
    @ApiOperation(value = "Banner 分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题，模糊匹配", example = "活动 A"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 1 开始", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<AdminsBannerPageVO> page(@RequestParam(value = "title", required = false) String title,
                                                 @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        CommonResult<BannerPageBO> result = bannerService.getBannerPage(new BannerPageDTO().setTitle(title).setPageNo(pageNo).setPageSize(pageSize));
        return BannerConvert.INSTANCE.convert(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建 Banner")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, example = "活动A"),
            @ApiImplicitParam(name = "url", value = "跳转链接", required = true, example = "http://www.iocoder.cn"),
            @ApiImplicitParam(name = "picUrl", value = "图片链接", required = true, example = "http://www.iocoder.cn/01.jpg"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, example = "10"),
            @ApiImplicitParam(name = "memo", value = "备注", required = true, example = "活动很牛逼"),
    })
    public CommonResult<AdminsBannerVO> add(@RequestParam("title") String title,
                                            @RequestParam("url") String url,
                                            @RequestParam("picUrl") String picUrl,
                                            @RequestParam("sort") Integer sort,
                                            @RequestParam(value = "memo", required = false) String memo) {
        BannerAddDTO bannerAddDTO = new BannerAddDTO().setTitle(title).setUrl(url).setPicUrl(picUrl)
                .setSort(sort).setMemo(memo);
        return BannerConvert.INSTANCE.convert2(bannerService.addBanner(AdminSecurityContextHolder.getContext().getAdminId(), bannerAddDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新 Banner")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Banner 编号", required = true, example = "1"),
            @ApiImplicitParam(name = "url", value = "跳转链接", required = true, example = "http://www.iocoder.cn"),
            @ApiImplicitParam(name = "picUrl", value = "图片链接", required = true, example = "http://www.iocoder.cn/01.jpg"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, example = "10"),
            @ApiImplicitParam(name = "memo", value = "备注", required = true, example = "活动很牛逼"),
    })
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("title") String title,
                                        @RequestParam("url") String url,
                                        @RequestParam("picUrl") String picUrl,
                                        @RequestParam("sort") Integer sort,
                                        @RequestParam(value = "memo", required = false) String memo) {
        BannerUpdateDTO bannerUpdateDTO = new BannerUpdateDTO().setId(id).setTitle(title).setUrl(url).setPicUrl(picUrl)
                .setSort(sort).setMemo(memo);
        return bannerService.updateBanner(AdminSecurityContextHolder.getContext().getAdminId(), bannerUpdateDTO);
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "更新 Banner 状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Banner 编号", required = true, example = "1"),
            @ApiImplicitParam(name = "status", value = "状态。1 - 开启；2 - 禁用", required = true, example = "1"),
    })
    public CommonResult<Boolean> updateStatus(@RequestParam("id") Integer id,
                                              @RequestParam("status") Integer status) {
        return bannerService.updateBannerStatus(AdminSecurityContextHolder.getContext().getAdminId(), id, status);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除 Banner")
    @ApiImplicitParam(name = "id", value = "Banner 编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return bannerService.deleteBanner(AdminSecurityContextHolder.getContext().getAdminId(), id);
    }

}
