package cn.iocoder.mall.promotion.rest.controller.banner;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.biz.bo.banner.BannerListBO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerAddDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerListDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerUpdateDTO;
import cn.iocoder.mall.promotion.biz.service.banner.BannerService;
import cn.iocoder.mall.promotion.rest.convert.BannerConvert;
import cn.iocoder.mall.promotion.rest.request.banner.BannerAddRequest;
import cn.iocoder.mall.promotion.rest.request.banner.BannerListRequest;
import cn.iocoder.mall.promotion.rest.request.banner.BannerUpdateRequest;
import cn.iocoder.mall.promotion.rest.request.banner.BannerUpdateStatusRequest;
import cn.iocoder.mall.promotion.rest.response.banner.BannerListResponse;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Banner(管理员API)
 *
 * author: sin
 * time: 2020/5/14 15:27
 */
@RestController
@RequestMapping("/admins/banner")
@Api(tags = "Banner(管理员API)")
public class AdminsBannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping("/list")
    @ApiOperation(value = "列表-banner列表")
    public CommonResult<PageResult<BannerListResponse>> page(@RequestBody @Valid BannerListRequest request) {
        // 获取数据
        BannerListDTO pageDTO = BannerConvert.INSTANCE.convert(request);
        PageResult<BannerListBO> pageResult = bannerService.listBanner(pageDTO);
        // 转换 response
        List<BannerListResponse> responseList = BannerConvert.INSTANCE.convert(pageResult.getList());
        return CommonResult.success(new PageResult<BannerListResponse>().setList(responseList).setTotal(pageResult.getTotal()));
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加-Banner")
    public CommonResult<Void> add(@RequestBody @Valid BannerAddRequest request) {
        BannerAddDTO bannerAddDTO = BannerConvert.INSTANCE.convert(request);
        bannerAddDTO.setAdminId(AdminSecurityContextHolder.getContext().getAdminId());
        bannerService.addBanner(bannerAddDTO);
        return CommonResult.success(null);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新-Banner信息")
    public CommonResult<Void> update(@RequestBody @Valid BannerUpdateRequest request) {
        BannerUpdateDTO bannerUpdateDTO = BannerConvert.INSTANCE.convert(request);
        bannerUpdateDTO.setAdminId(AdminSecurityContextHolder.getContext().getAdminId());
        bannerService.updateBanner(bannerUpdateDTO);
        return CommonResult.success(null);
    }

    @PutMapping("/update-status")
    @ApiOperation(value = "更新-banner状态")
    public CommonResult<Void> updateStatus(@RequestBody @Valid BannerUpdateStatusRequest request) {
        Integer adminId = AdminSecurityContextHolder.getContext().getAdminId();
        bannerService.updateBannerStatus(adminId, request.getBannerId(), request.getStatus());
        return CommonResult.success(null);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除-根据id删除")
    @ApiImplicitParam(name = "id", value = "Banner 编号", required = true, example = "1")
    public CommonResult<Void> delete(@RequestParam("id") Integer id) {
        bannerService.deleteBanner(AdminSecurityContextHolder.getContext().getAdminId(), id);
        return CommonResult.success(null);
    }
}
