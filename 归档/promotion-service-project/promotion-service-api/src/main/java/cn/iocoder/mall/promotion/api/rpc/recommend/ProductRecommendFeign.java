package cn.iocoder.mall.promotion.api.rpc.recommend;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient("promotion-service")
public interface ProductRecommendFeign {

    @PostMapping("/promotion/prod/recommend/createProductRecommend")
    public CommonResult<Integer> createProductRecommend(@RequestBody ProductRecommendCreateReqDTO createDTO) ;

    @PostMapping("/promotion/prod/recommend/updateProductRecommend")
    public CommonResult<Boolean> updateProductRecommend(@RequestBody ProductRecommendUpdateReqDTO updateDTO);

    @GetMapping("/promotion/prod/recommend/deleteProductRecommend")
    public CommonResult<Boolean> deleteProductRecommend(@RequestParam("productRecommendId") Integer productRecommendId) ;

    @PostMapping("/promotion/prod/recommend/listProductRecommends")
    public CommonResult<List<ProductRecommendRespDTO>> listProductRecommends(@RequestBody ProductRecommendListReqDTO listReqDTO) ;

    @PostMapping("/promotion/prod/recommend/pageProductRecommend")
    public CommonResult<PageResult<ProductRecommendRespDTO>> pageProductRecommend(@RequestBody ProductRecommendPageReqDTO pageDTO) ;
}
