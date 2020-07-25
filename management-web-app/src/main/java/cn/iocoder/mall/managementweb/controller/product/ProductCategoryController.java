package cn.iocoder.mall.managementweb.controller.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryUpdateReqVO;
import cn.iocoder.mall.managementweb.manager.product.ProductCategoryManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 商品分类 Controller
*/
@RestController
@RequestMapping("/product_category")
@Api(tags = "商品分类")
@Validated
public class ProductCategoryController {

    @Autowired
    private ProductCategoryManager productCategoryManager;

    @PostMapping("/create")
    @ApiOperation("创建商品分类")
    public CommonResult<Integer> createProductCategory(@Valid ProductCategoryCreateReqVO createVO) {
        return success(productCategoryManager.createProductCategory(createVO));
    }

    @PostMapping("/update")
    @ApiOperation("更新商品分类")
    public CommonResult<Boolean> updateProductCategory(@Valid ProductCategoryUpdateReqVO updateVO) {
        productCategoryManager.updateProductCategory(updateVO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除商品分类")
    @ApiImplicitParam(name = "productCategoryId", value = "商品分类编号", required = true)
    public CommonResult<Boolean> deleteProductCategory(@RequestParam("productCategoryId") Integer productCategoryId) {
        productCategoryManager.deleteProductCategory(productCategoryId);
        return success(true);
    }

}
