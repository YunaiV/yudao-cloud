package cn.iocoder.mall.managementweb.controller.promotion.recommend;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.recommend.vo.ProductRecommendCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.recommend.vo.ProductRecommendDetailVO;
import cn.iocoder.mall.managementweb.controller.promotion.recommend.vo.ProductRecommendPageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.recommend.vo.ProductRecommendUpdateReqVO;
import cn.iocoder.mall.managementweb.manager.promotion.recommend.ProductRecommendManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * 商品推荐 Controller
 */
@RestController
@RequestMapping("/promotion/product-recommend")
@Api(tags = "商品推荐")
@Validated
public class ProductRecommendController {

    @Autowired
    private ProductRecommendManager productRecommendManager;

    @PostMapping("/create")
    @ApiOperation("创建商品推荐")
    public CommonResult<Integer> createProductRecommend(@Valid ProductRecommendCreateReqVO createVO) {
        return success(productRecommendManager.createProductRecommend(createVO));
    }

    @PostMapping("/update")
    @ApiOperation("更新商品推荐")
    public CommonResult<Boolean> updateProductRecommend(@Valid ProductRecommendUpdateReqVO updateVO) {
        productRecommendManager.updateProductRecommend(updateVO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除商品推荐")
    @ApiImplicitParam(name = "productRecommendId", value = "商品推荐编号", required = true)
    public CommonResult<Boolean> deleteProductRecommend(@RequestParam("productRecommendId") Integer productRecommendId) {
        productRecommendManager.deleteProductRecommend(productRecommendId);
        return success(true);
    }

    @GetMapping("/page")
    @ApiOperation("获得商品推荐分页")
    public CommonResult<PageResult<ProductRecommendDetailVO>> pageProductRecommend(ProductRecommendPageReqVO pageVO) {
        return success(productRecommendManager.pageProductRecommend(pageVO));
    }

}
