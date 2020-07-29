package cn.iocoder.mall.promotionservice.service.recommend;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.productservice.rpc.spu.ProductSpuRpc;
import cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeEnum;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendPageRespDTO;
import cn.iocoder.mall.promotionservice.convert.recommend.ProductRecommendConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.recommend.ProductRecommendDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.recommend.ProductRecommendMapper;
import cn.iocoder.mall.promotionservice.service.recommend.bo.ProductRecommendAddBO;
import cn.iocoder.mall.promotionservice.service.recommend.bo.ProductRecommendBO;
import cn.iocoder.mall.promotionservice.service.recommend.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotionservice.service.recommend.bo.ProductRecommendUpdateBO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Service
@Validated
public class ProductRecommendService {

    @Reference(validation = "true", version = "${dubbo.consumer.ProductSpuService.version}")
    private ProductSpuRpc productSpuRpc;

    @Autowired
    private ProductRecommendMapper productRecommendMapper;

    public List<ProductRecommendBO> getProductRecommendList(Integer type, Integer status) {
        List<ProductRecommendDO> productRecommends = productRecommendMapper.selectListByTypeAndStatus(type, status);
        return ProductRecommendConvert.INSTANCE.convertToBO(productRecommends);
    }

    public ProductRecommendPageRespDTO getProductRecommendPage(ProductRecommendPageReqDTO productRecommendPageDTO) {
        ProductRecommendPageRespDTO productRecommendPageBO = new ProductRecommendPageRespDTO();
        // 查询分页数据
        int offset = (productRecommendPageDTO.getPageNo() - 1) * productRecommendPageDTO.getPageSize();
        productRecommendPageBO.setList(ProductRecommendConvert.INSTANCE.convertToDTO(productRecommendMapper.selectPageByType(productRecommendPageDTO.getType(),
                offset, productRecommendPageDTO.getPageSize())));
        // 查询分页总数
        productRecommendPageBO.setTotal(productRecommendMapper.selectCountByType(productRecommendPageDTO.getType()));
        return productRecommendPageBO;
    }

    public ProductRecommendBO addProductRecommend(Integer adminId, ProductRecommendAddBO productRecommendAddDTO) {
        // 校验商品不存在
        if (productSpuRpc.getProductSpu(productRecommendAddDTO.getProductSpuId()) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.PRODUCT_RECOMMEND_PRODUCT_NOT_EXISTS.getCode());
        }
        // 校验商品是否已经推荐
        if (productRecommendMapper.selectByProductSpuIdAndType(productRecommendAddDTO.getProductSpuId(), productRecommendAddDTO.getType()) != null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.PRODUCT_RECOMMEND_EXISTS.getCode());
        }
        // 保存到数据库
        ProductRecommendDO productRecommend = ProductRecommendConvert.INSTANCE.convert(productRecommendAddDTO).setStatus(CommonStatusEnum.ENABLE.getValue());
        productRecommend.setDeleted(DeletedStatusEnum.DELETED_NO.getValue()).setCreateTime(new Date());
        productRecommendMapper.insert(productRecommend);
        // 返回成功
        return ProductRecommendConvert.INSTANCE.convertToBO(productRecommend);
    }

    public Boolean updateProductRecommend(Integer adminId, ProductRecommendUpdateBO productRecommendUpdateDTO) {
        // 校验更新的商品推荐存在
        if (productRecommendMapper.selectById(productRecommendUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.PRODUCT_RECOMMEND_NOT_EXISTS.getCode());
        }
        // 校验商品不存在
        if (productSpuRpc.getProductSpu(productRecommendUpdateDTO.getProductSpuId()) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.PRODUCT_RECOMMEND_PRODUCT_NOT_EXISTS.getCode());
        }
        // 校验商品是否已经推荐
        ProductRecommendDO existProductRecommend = productRecommendMapper.selectByProductSpuIdAndType(productRecommendUpdateDTO.getProductSpuId(), productRecommendUpdateDTO.getType());
        if (existProductRecommend != null && !existProductRecommend.getId().equals(productRecommendUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.PRODUCT_RECOMMEND_EXISTS.getCode());
        }
        // 更新到数据库
        ProductRecommendDO updateProductRecommend = ProductRecommendConvert.INSTANCE.convert(productRecommendUpdateDTO);
        productRecommendMapper.update(updateProductRecommend);
        // 返回成功
        return true;
    }

    public Boolean updateProductRecommendStatus(Integer adminId, Integer productRecommendId, Integer status) {
        // 校验更新的商品推荐存在
        if (productRecommendMapper.selectById(productRecommendId) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.PRODUCT_RECOMMEND_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        ProductRecommendDO updateProductRecommend = new ProductRecommendDO().setId(productRecommendId).setStatus(status);
        productRecommendMapper.update(updateProductRecommend);
        // 返回成功
        return true;
    }

    public Boolean deleteProductRecommend(Integer adminId, Integer productRecommendId) {
        // 校验更新的商品推荐存在
        if (productRecommendMapper.selectById(productRecommendId) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.PRODUCT_RECOMMEND_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        ProductRecommendDO updateProductRecommend = new ProductRecommendDO().setId(productRecommendId);
        updateProductRecommend.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        productRecommendMapper.update(updateProductRecommend);
        // 返回成功
        return true;
    }

}
