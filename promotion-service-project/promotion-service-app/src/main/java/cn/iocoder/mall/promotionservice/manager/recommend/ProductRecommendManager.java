package cn.iocoder.mall.promotionservice.manager.recommend;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.spu.ProductSpuRpc;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.*;
import cn.iocoder.mall.promotionservice.service.recommend.ProductRecommendService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeConstants.PRODUCT_RECOMMEND_PRODUCT_NOT_EXISTS;

/**
 * 商品推荐 Manager
 */
@Service
@Validated
public class ProductRecommendManager {

    @DubboReference(validation = "true", version = "${dubbo.consumer.ProductSpuRpc.version}")
    private ProductSpuRpc productSpuRpc;

    @Autowired
    private ProductRecommendService productRecommendService;

    public List<ProductRecommendRespDTO> listProductRecommends(ProductRecommendListReqDTO listReqDTO) {
        return productRecommendService.listProductRecommends(listReqDTO);
    }

    public PageResult<ProductRecommendRespDTO> pageProductRecommend(ProductRecommendPageReqDTO pageReqDTO) {
        return productRecommendService.pageProductRecommend(pageReqDTO);
    }

    public Integer createProductRecommend(ProductRecommendCreateReqDTO createReqDTO) {
        // 校验商品不存在
        checkProductSpu(createReqDTO.getProductSpuId());
        // 创建商品推荐
        return  productRecommendService.createProductRecommend(createReqDTO);
    }

    public void updateProductRecommend(ProductRecommendUpdateReqDTO updateReqDTO) {
        // 校验商品不存在
        checkProductSpu(updateReqDTO.getProductSpuId());
        // 更新商品推荐
        productRecommendService.updateProductRecommend(updateReqDTO);
    }

    public void deleteProductRecommend(Integer productRecommendId) {
        productRecommendService.deleteProductRecommend(productRecommendId);
    }

    private void checkProductSpu(Integer productSpuId) {
        CommonResult<ProductSpuRespDTO> getProductSpuResult = productSpuRpc.getProductSpu(productSpuId);
        getProductSpuResult.checkError();
        if (getProductSpuResult.getData() == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_RECOMMEND_PRODUCT_NOT_EXISTS);
        }
    }

}
