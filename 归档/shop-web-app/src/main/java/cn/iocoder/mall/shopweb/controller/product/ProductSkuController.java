package cn.iocoder.mall.shopweb.controller.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.shopweb.controller.product.vo.sku.ProductSkuCalcPriceRespVO;
import cn.iocoder.mall.shopweb.service.product.ProductSkuManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品 SKU API")
@RestController
@RequestMapping("/product-sku")
@Validated
public class ProductSkuController {

    @Autowired
    private ProductSkuManager productSkuManager;

    @GetMapping("/cal-price")
    @ApiOperation("计算商品 SKU 价格")
    @ApiImplicitParam(name = "id", required = true, value = "商品 SKU 编号", example = "1024")
    public CommonResult<ProductSkuCalcPriceRespVO> calcProductSkuPrice(@RequestParam("id") Integer id) {
        return CommonResult.success(productSkuManager.calcProductSkuPrice(UserSecurityContextHolder.getUserId(), id));
    }

}
