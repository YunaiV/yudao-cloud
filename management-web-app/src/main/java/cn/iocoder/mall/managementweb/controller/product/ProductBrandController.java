package cn.iocoder.mall.managementweb.controller.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandUpdateReqVO;
import cn.iocoder.mall.managementweb.manager.product.ProductBrandManager;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 商品品牌 Controller
*/
@RestController
@RequestMapping("/product-brand")
@Api(tags = "商品品牌")
@Validated
public class ProductBrandController {

    @Autowired
    private ProductBrandManager productBrandManager;

    @PostMapping("/create")
    @ApiOperation("创建商品品牌")
    @RequiresPermissions("product:brand:create")
    public CommonResult<Integer> createProductBrand(@Valid ProductBrandCreateReqVO createVO) {
        return success(productBrandManager.createProductBrand(createVO));
    }

    @PostMapping("/update")
    @ApiOperation("更新商品品牌")
    @RequiresPermissions("product:brand:update")
    public CommonResult<Boolean> updateProductBrand(@Valid ProductBrandUpdateReqVO updateVO) {
        productBrandManager.updateProductBrand(updateVO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除商品品牌")
    @ApiImplicitParam(name = "productBrandId", value = "商品品牌编号", required = true)
    @RequiresPermissions("product:brand:delete")
    public CommonResult<Boolean> deleteProductBrand(@RequestParam("productBrandId") Integer productBrandId) {
        productBrandManager.deleteProductBrand(productBrandId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得商品品牌")
    @ApiImplicitParam(name = "productBrandId", value = "商品品牌编号", required = true)
    @RequiresPermissions("product:brand:page")
    public CommonResult<ProductBrandRespVO> getProductBrand(@RequestParam("productBrandId") Integer productBrandId) {
        return success(productBrandManager.getProductBrand(productBrandId));
    }

    @GetMapping("/list")
    @ApiOperation("获得商品品牌列表")
    @ApiImplicitParam(name = "productBrandIds", value = "商品品牌编号列表", required = true)
    @RequiresPermissions("product:brand:page")
    public CommonResult<List<ProductBrandRespVO>> listProductBrands(@RequestParam("productBrandIds") List<Integer> productBrandIds) {
        return success(productBrandManager.listProductBrands(productBrandIds));
    }

    @GetMapping("/page")
    @ApiOperation("获得商品品牌分页")
    @RequiresPermissions("product:brand:page")
    public CommonResult<PageResult<ProductBrandRespVO>> pageProductBrand(ProductBrandPageReqVO pageVO) {
        return success(productBrandManager.pageProductBrand(pageVO));
    }

}
