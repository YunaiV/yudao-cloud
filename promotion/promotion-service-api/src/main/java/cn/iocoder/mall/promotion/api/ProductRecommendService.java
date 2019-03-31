package cn.iocoder.mall.promotion.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendAddDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendPageDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendUpdateDTO;

import java.util.List;

public interface ProductRecommendService {

    CommonResult<List<ProductRecommendBO>> getProductRecommendList(Integer type, Integer status);

    CommonResult<ProductRecommendPageBO> getProductRecommendPage(ProductRecommendPageDTO productRecommendPageDTO);

    CommonResult<ProductRecommendBO> addProductRecommend(Integer adminId, ProductRecommendAddDTO productRecommendAddDTO);

    CommonResult<Boolean> updateProductRecommend(Integer adminId, ProductRecommendUpdateDTO productRecommendUpdateDTO);

    CommonResult<Boolean> updateProductRecommendStatus(Integer adminId, Integer productRecommendId, Integer status);

    CommonResult<Boolean> deleteProductRecommend(Integer adminId, Integer productRecommendId);

}