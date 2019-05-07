package cn.iocoder.mall.promotion.biz.service;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.promotion.api.ProductRecommendService;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotion.api.constant.PromotionErrorCodeEnum;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendAddDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendPageDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendUpdateDTO;
import cn.iocoder.mall.promotion.biz.convert.ProductRecommendConvert;
import cn.iocoder.mall.promotion.biz.dao.ProductRecommendMapper;
import cn.iocoder.mall.promotion.biz.dataobject.ProductRecommendDO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductRecommendService.version}")
public class ProductRecommendServiceImpl implements ProductRecommendService {

    @Reference(validation = "true", version = "${dubbo.consumer.ProductSpuService.version}")
    private ProductSpuService productSpuService;

    @Autowired
    private ProductRecommendMapper productRecommendMapper;

    @Override
    public List<ProductRecommendBO> getProductRecommendList(Integer type, Integer status) {
        List<ProductRecommendDO> productRecommends = productRecommendMapper.selectListByTypeAndStatus(type, status);
        return ProductRecommendConvert.INSTANCE.convertToBO(productRecommends);
    }

    @Override
    public ProductRecommendPageBO getProductRecommendPage(ProductRecommendPageDTO productRecommendPageDTO) {
        ProductRecommendPageBO productRecommendPageBO = new ProductRecommendPageBO();
        // 查询分页数据
        int offset = (productRecommendPageDTO.getPageNo() - 1) * productRecommendPageDTO.getPageSize();
        productRecommendPageBO.setList(ProductRecommendConvert.INSTANCE.convertToBO(productRecommendMapper.selectPageByType(productRecommendPageDTO.getType(),
                offset, productRecommendPageDTO.getPageSize())));
        // 查询分页总数
        productRecommendPageBO.setTotal(productRecommendMapper.selectCountByType(productRecommendPageDTO.getType()));
        return productRecommendPageBO;
    }

    @Override
    public ProductRecommendBO addProductRecommend(Integer adminId, ProductRecommendAddDTO productRecommendAddDTO) {
        // 校验商品不存在
        if (productSpuService.getProductSpuDetail(productRecommendAddDTO.getProductSpuId()) == null) {
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

    @Override
    public Boolean updateProductRecommend(Integer adminId, ProductRecommendUpdateDTO productRecommendUpdateDTO) {
        // 校验更新的商品推荐存在
        if (productRecommendMapper.selectById(productRecommendUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.PRODUCT_RECOMMEND_NOT_EXISTS.getCode());
        }
        // 校验商品不存在
        if (productSpuService.getProductSpuDetail(productRecommendUpdateDTO.getProductSpuId()) == null) {
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

    @Override
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

    @Override
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
