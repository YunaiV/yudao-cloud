package cn.iocoder.mall.promotion.api.rpc.recommend.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.validator.InEnum;

import java.util.List;

public interface ProductRecommendRpc {

    List<ProductRecommendRespDTO> getProductRecommendList(Integer type, Integer status);

    ProductRecommendPageRespDTO getProductRecommendPage(ProductRecommendPageReqDTO productRecommendPageDTO);

    ProductRecommendRespDTO addProductRecommend(Integer adminId, ProductRecommendAddReqDTO productRecommendAddDTO) throws ServiceException;

    Boolean updateProductRecommend(Integer adminId, ProductRecommendUpdateReqDTO productRecommendUpdateDTO) throws ServiceException;

    Boolean updateProductRecommendStatus(Integer adminId, Integer productRecommendId,
                                         @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status) throws ServiceException;

    Boolean deleteProductRecommend(Integer adminId, Integer productRecommendId);

}
