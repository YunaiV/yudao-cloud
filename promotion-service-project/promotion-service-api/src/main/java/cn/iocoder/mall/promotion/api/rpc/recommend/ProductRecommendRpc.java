package cn.iocoder.mall.promotion.api.rpc.recommend;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.*;

import java.util.List;

/**
 * 商品推荐 Rpc 接口
 */
public interface ProductRecommendRpc {

    /**
     * 创建商品推荐
     *
     * @param createDTO 创建商品推荐 DTO
     * @return 商品推荐编号
     */
    CommonResult<Integer> createProductRecommend(ProductRecommendCreateReqDTO createDTO);

    /**
     * 更新商品推荐
     *
     * @param updateDTO 更新商品推荐 DTO
     */
    CommonResult<Boolean> updateProductRecommend(ProductRecommendUpdateReqDTO updateDTO);

    /**
     * 删除商品推荐
     *
     * @param productRecommendId 商品推荐编号
     */
    CommonResult<Boolean> deleteProductRecommend(Integer productRecommendId);

    /**
     * 获得商品推荐列表
     *
     * @param listReqDTO 商品推荐列表查询 DTO
     * @return 商品推荐列表
     */
    CommonResult<List<ProductRecommendRespDTO>> listProductRecommends(ProductRecommendListReqDTO listReqDTO);

    /**
     * 获得商品推荐分页
     *
     * @param pageDTO 商品推荐分页查询
     * @return 商品推荐分页结果
     */
    CommonResult<PageResult<ProductRecommendRespDTO>> pageProductRecommend(ProductRecommendPageReqDTO pageDTO);

}
