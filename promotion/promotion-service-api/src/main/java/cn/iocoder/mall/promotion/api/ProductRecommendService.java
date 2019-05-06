package cn.iocoder.mall.promotion.api;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendAddDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendPageDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendUpdateDTO;

import java.util.List;

public interface ProductRecommendService {

    List<ProductRecommendBO> getProductRecommendList(Integer type, Integer status);

    ProductRecommendPageBO getProductRecommendPage(ProductRecommendPageDTO productRecommendPageDTO);

    ProductRecommendBO addProductRecommend(Integer adminId, ProductRecommendAddDTO productRecommendAddDTO);

    Boolean updateProductRecommend(Integer adminId, ProductRecommendUpdateDTO productRecommendUpdateDTO);

    Boolean updateProductRecommendStatus(Integer adminId, Integer productRecommendId,
                                         @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    Boolean deleteProductRecommend(Integer adminId, Integer productRecommendId);

}
