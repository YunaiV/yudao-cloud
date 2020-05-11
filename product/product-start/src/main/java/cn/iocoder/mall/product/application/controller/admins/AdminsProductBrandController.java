package cn.iocoder.mall.product.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductBrandService;
import cn.iocoder.mall.product.api.bo.ProductBrandBO;
import cn.iocoder.mall.product.api.bo.ProductBrangPageBO;
import cn.iocoder.mall.product.api.dto.ProductBrandAddDTO;
import cn.iocoder.mall.product.api.dto.ProductBrandPageDTO;
import cn.iocoder.mall.product.api.dto.ProductBrandUpdateDTO;
import cn.iocoder.mall.product.application.convert.ProductBrandConvert;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductBrandVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductBrangPageVO;
import cn.iocoder.mall.system.sdk.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/brand")
@Api("商品品牌")
public class AdminsProductBrandController {

    @Reference(validation = "true", version = "${dubbo.provider.ProductBrandService.version}")
    private ProductBrandService productBrandService;

    @PostMapping("/add")
    @ApiOperation("创建品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "品牌名称", required = true, example = "安踏"),
            @ApiImplicitParam(name = "description", value = "品牌描述", required = true, example = "安踏拖鞋"),
            @ApiImplicitParam(name = "picUrl", value = "品牌图片", required = true, example = "http://www.iocoder.cn"),
            @ApiImplicitParam(name = "status", value = "状态 1开启 2禁用", required = true, example = "1")
    })
    public CommonResult<AdminsProductBrandVO> add(@RequestParam("name") String name,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("picUrl") String picUrl,
                                                  @RequestParam("status") Integer status) {
        // 创建 ProductBrandAddDTO 对象
        ProductBrandAddDTO productBrandAddDTO = new ProductBrandAddDTO().setName(name).setDescription(description)
                .setPicUrl(picUrl).setStatus(status);
        // 保存商品
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
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String description,
                                        @RequestParam("picUrl") String picUrl,
                                        @RequestParam("status") Integer status) {
        // 创建 productBrandUpdateDTO 对象
        ProductBrandUpdateDTO productBrandUpdateDTO = new ProductBrandUpdateDTO().setId(id).setName(name).setDescription(description)
                .setPicUrl(picUrl).setStatus(status);
        // 更新商品
        productBrandService.updateProductBrand(AdminSecurityContextHolder.getContext().getAdminId(), productBrandUpdateDTO);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获取品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "品牌主键", required = true, example = "1")
    })
    public CommonResult<AdminsProductBrandVO> add(@RequestParam("id") Integer id) {
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
    public CommonResult<AdminsProductBrangPageVO> attrPage(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "description", required = false) String description,
                                                           @RequestParam(value = "status", required = false) Integer status,
                                                           @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        // 创建 ProductAttrPageDTO 对象
        ProductBrandPageDTO productBrandPageDTO = new ProductBrandPageDTO().setName(name)
                .setDescription(description)
                .setStatus(status)
                .setPageNo(pageNo)
                .setPageSize(pageSize);
        // 查询分页
        ProductBrangPageBO result = productBrandService.getProductBrandPage(productBrandPageDTO);
        // 返回结果
        return success(ProductBrandConvert.INSTANCE.convert(result));
    }


}
