package cn.iocoder.mall.shopweb.controller.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuRespVO;
import cn.iocoder.mall.shopweb.service.promotion.ProductRecommendManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/promotion/product-recommend")
@Api(tags = "商品推荐 API")
@Validated
public class ProductRecommendController {

    @Autowired
    private ProductRecommendManager productRecommendManager;

    @GetMapping("/list")
    @ApiOperation("获得所有商品推荐列表")
    public CommonResult<Map<Integer, Collection<ProductSpuRespVO>>> list() {
        return success(productRecommendManager.listProductRecommends());
    }

}
