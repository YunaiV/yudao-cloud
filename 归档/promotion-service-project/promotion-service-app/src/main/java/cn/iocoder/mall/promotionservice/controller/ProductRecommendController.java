package cn.iocoder.mall.promotionservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.*;
import cn.iocoder.mall.promotionservice.manager.recommend.ProductRecommendManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/promotion/prod/recommend")
public class ProductRecommendController {

    @Autowired
    private ProductRecommendManager productRecommendManager;


    @PostMapping("createProductRecommend")
    public CommonResult<Integer> createProductRecommend(@RequestBody ProductRecommendCreateReqDTO createDTO) {
        return success(productRecommendManager.createProductRecommend(createDTO));
    }

    @PostMapping("updateProductRecommend")
    public CommonResult<Boolean> updateProductRecommend(@RequestBody ProductRecommendUpdateReqDTO updateDTO) {
        productRecommendManager.updateProductRecommend(updateDTO);
        return success(true);
    }

    @GetMapping("deleteProductRecommend")
    public CommonResult<Boolean> deleteProductRecommend(@RequestParam("productRecommendId") Integer productRecommendId) {
        productRecommendManager.deleteProductRecommend(productRecommendId);
        return success(true);
    }

    @PostMapping("listProductRecommends")
    public CommonResult<List<ProductRecommendRespDTO>> listProductRecommends(@RequestBody ProductRecommendListReqDTO listReqDTO) {
        return success(productRecommendManager.listProductRecommends(listReqDTO));
    }

    @PostMapping("pageProductRecommend")
    public CommonResult<PageResult<ProductRecommendRespDTO>> pageProductRecommend(@RequestBody ProductRecommendPageReqDTO pageDTO) {
        return success(productRecommendManager.pageProductRecommend(pageDTO));
    }
}
