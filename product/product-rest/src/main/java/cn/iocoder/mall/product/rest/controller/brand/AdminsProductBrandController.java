package cn.iocoder.mall.product.rest.controller.brand;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.brand.ProductBrandBO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandAddDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandPageDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandUpdateDTO;
import cn.iocoder.mall.product.biz.service.product.ProductBrandService;
import cn.iocoder.mall.product.rest.convert.brand.ProductBrandConvert;
import cn.iocoder.mall.product.rest.request.brand.ProductBrandAddRequest;
import cn.iocoder.mall.product.rest.request.brand.ProductBrandPageRequest;
import cn.iocoder.mall.product.rest.request.brand.ProductBrandUpdateRequest;
import cn.iocoder.mall.product.rest.response.brand.AdminsProductBrandResponse;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/brand")
@Api("管理员 - 商品品牌 API")
@AllArgsConstructor
public class AdminsProductBrandController {

    private final ProductBrandService productBrandService;

    @PostMapping("/add")
    @ApiOperation("创建品牌")
    public CommonResult<AdminsProductBrandResponse> add(@Validated ProductBrandAddRequest addRequest) {
        // 创建 ProductBrandAddDTO 对象
        ProductBrandAddDTO productBrandAddDTO = ProductBrandConvert.INSTANCE.convertAdd(addRequest);
        // 保存品牌
        ProductBrandBO result = productBrandService.addProductBrand(AdminSecurityContextHolder.getContext().getAdminId(), productBrandAddDTO);
        // 返回结果
        return success(ProductBrandConvert.INSTANCE.convert(result));
    }

    @PostMapping("/update")
    @ApiOperation("更新商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "品牌主键", required = true, example = "1"),
            @ApiImplicitParam(name = "name", value = "品牌名称", required = true, example = "安踏"),
            @ApiImplicitParam(name = "description", value = "品牌描述", required = true, example = "安踏拖鞋"),
            @ApiImplicitParam(name = "picUrl", value = "品牌图片", required = true, example = "http://www.iocoder.cn"),
            @ApiImplicitParam(name = "status", value = "状态 1开启 2禁用", required = true, example = "1")
    })
    // TODO FROM 芋艿 to q2118cs：只要改成了 bean 接收，就不用在写 @ApiImplicitParam 注解啦，直接在 bean 里写就 ok 啦
    public CommonResult<Boolean> update(@Validated ProductBrandUpdateRequest updateRequest) {
        // 创建 productBrandUpdateDTO 对象
        ProductBrandUpdateDTO productBrandUpdateDTO = ProductBrandConvert.INSTANCE.convertUpdate(updateRequest);
        // 更新商品
        return success(productBrandService.updateProductBrand(AdminSecurityContextHolder.getContext().getAdminId(), productBrandUpdateDTO));
    }

    @GetMapping("/get")
    @ApiOperation("获取品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "品牌主键", required = true, example = "1")
    })
    public CommonResult<AdminsProductBrandResponse> add(@RequestParam("id") Integer id) {
        // 保存商品
        ProductBrandBO result = productBrandService.getProductBrand(id);
        // 返回结果
        return success(ProductBrandConvert.INSTANCE.convert(result));
    }

    @GetMapping("/page")
    @ApiOperation("获得品牌分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "品牌名称", required = true, example = "安踏"),
            @ApiImplicitParam(name = "description", value = "品牌描述", required = true, example = "安踏拖鞋"),
            @ApiImplicitParam(name = "status", value = "状态 1开启 2禁用", required = true, example = "1"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, example = "10")
    })
    public CommonResult<PageResult<AdminsProductBrandResponse>> attrPage(ProductBrandPageRequest pageRequest) {
        // 创建 ProductBrandPageDTO 对象
        ProductBrandPageDTO productBrandPageDTO = ProductBrandConvert.INSTANCE.convertPageRequest(pageRequest);
        // 查询分页
        PageResult<ProductBrandBO> productBrandPage = productBrandService.getProductBrandPage(productBrandPageDTO);
        PageResult<AdminsProductBrandResponse> adminPageResponse = ProductBrandConvert.INSTANCE.convertPage(productBrandPage);
        return CommonResult.success(adminPageResponse);
    }


}
