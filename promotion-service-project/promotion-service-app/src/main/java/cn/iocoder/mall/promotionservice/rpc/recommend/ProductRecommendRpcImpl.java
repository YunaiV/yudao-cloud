package cn.iocoder.mall.promotionservice.rpc.recommend;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.recommend.ProductRecommendRpc;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.*;
import cn.iocoder.mall.promotionservice.manager.recommend.ProductRecommendManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@DubboService
public class ProductRecommendRpcImpl implements ProductRecommendRpc {

    @Autowired
    private ProductRecommendManager productRecommendManager;

    @Override
    public CommonResult<Integer> createProductRecommend(ProductRecommendCreateReqDTO createDTO) {
        return success(productRecommendManager.createProductRecommend(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateProductRecommend(ProductRecommendUpdateReqDTO updateDTO) {
        productRecommendManager.updateProductRecommend(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteProductRecommend(Integer productRecommendId) {
        productRecommendManager.deleteProductRecommend(productRecommendId);
        return success(true);
    }

    @Override
    public CommonResult<List<ProductRecommendRespDTO>> listProductRecommends(ProductRecommendListReqDTO listReqDTO) {
        return success(productRecommendManager.listProductRecommends(listReqDTO));
    }

    @Override
    public CommonResult<PageResult<ProductRecommendRespDTO>> pageProductRecommend(ProductRecommendPageReqDTO pageDTO) {
        return success(productRecommendManager.pageProductRecommend(pageDTO));
    }

}
