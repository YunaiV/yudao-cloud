package cn.iocoder.mall.promotion.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import cn.iocoder.mall.promotion.api.ProductRecommendService;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendAddDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendPageDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendUpdateDTO;
import cn.iocoder.mall.promotion.application.convert.ProductRecommendConvert;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendVO;
import org.apache.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admins/product_recommend")
@Api("商品推荐模块")
public class AdminsProductRecommendController {

    @Reference(validation = "true")
    @Autowired
    private ProductRecommendService productRecommendService;

    @GetMapping("/page")
    @ApiOperation(value = "商品推荐分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "推荐类型", example = "1"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 1 开始", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<AdminsProductRecommendPageVO> page(@RequestParam(value = "type", required = false) Integer type,
                                                 @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        CommonResult<ProductRecommendPageBO> result = productRecommendService.getProductRecommendPage(new ProductRecommendPageDTO().setType(type).setPageNo(pageNo).setPageSize(pageSize));
        return ProductRecommendConvert.INSTANCE.convert(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建商品推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "推荐类型", required = true, example = "1"),
            @ApiImplicitParam(name = "productSpuId", value = "商品编号", required = true, example = "1"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, example = "10"),
            @ApiImplicitParam(name = "memo", value = "备注", example = "活动很牛逼"),
    })
    public CommonResult<AdminsProductRecommendVO> add(@RequestParam("type") Integer type,
                                                      @RequestParam("productSpuId") Integer productSpuId,
                                                      @RequestParam("sort") Integer sort,
                                                      @RequestParam(value = "memo", required = false) String memo) {
        ProductRecommendAddDTO bannerAddDTO = new ProductRecommendAddDTO().setType(type).setProductSpuId(productSpuId)
                .setSort(sort).setMemo(memo);
        return ProductRecommendConvert.INSTANCE.convert2(productRecommendService.addProductRecommend(AdminSecurityContextHolder.getContext().getAdminId(), bannerAddDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新商品推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品推荐编号", required = true, example = "1"),
            @ApiImplicitParam(name = "type", value = "推荐类型", required = true, example = "1"),
            @ApiImplicitParam(name = "productSpuId", value = "商品编号", required = true, example = "1"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, example = "10"),
            @ApiImplicitParam(name = "memo", value = "备注", example = "活动很牛逼"),
    })
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("type") Integer type,
                                        @RequestParam("productSpuId") Integer productSpuId,
                                        @RequestParam("sort") Integer sort,
                                        @RequestParam(value = "memo", required = false) String memo) {
        ProductRecommendUpdateDTO bannerUpdateDTO = new ProductRecommendUpdateDTO().setId(id).setType(type).setProductSpuId(productSpuId)
                .setSort(sort).setMemo(memo);
        return productRecommendService.updateProductRecommend(AdminSecurityContextHolder.getContext().getAdminId(), bannerUpdateDTO);
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "更新商品推荐状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品推荐编号", required = true, example = "1"),
            @ApiImplicitParam(name = "status", value = "状态。1 - 开启；2 - 禁用", required = true, example = "1"),
    })
    public CommonResult<Boolean> updateStatus(@RequestParam("id") Integer id,
                                              @RequestParam("status") Integer status) {
        return productRecommendService.updateProductRecommendStatus(AdminSecurityContextHolder.getContext().getAdminId(), id, status);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除商品推荐")
    @ApiImplicitParam(name = "id", value = "商品推荐编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return productRecommendService.deleteProductRecommend(AdminSecurityContextHolder.getContext().getAdminId(), id);
    }

}
