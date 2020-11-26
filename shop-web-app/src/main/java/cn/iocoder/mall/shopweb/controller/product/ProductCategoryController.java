package cn.iocoder.mall.shopweb.controller.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.shopweb.controller.product.vo.category.ProductCategoryRespVO;
import cn.iocoder.mall.shopweb.service.product.ProductCategoryManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "商品分类 API")
@RestController
@RequestMapping("/product-category")
@Validated
public class ProductCategoryController {

    @Autowired
    private ProductCategoryManager productCategoryManager;

    @GetMapping("/list")
    @ApiOperation("获得商品分类的列表")
    @ApiImplicitParam(name = "pid", value = "父分类编号", required = true, example = "1024")
    public CommonResult<List<ProductCategoryRespVO>> listProductCategories(@RequestParam("pid") Integer pid) {
        return success(productCategoryManager.listProductCategories(pid));
    }

}
