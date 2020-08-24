package cn.iocoder.mall.promotionservice.service.recommend;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.*;
import cn.iocoder.mall.promotionservice.convert.recommend.ProductRecommendConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.recommend.ProductRecommendDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.recommend.ProductRecommendMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeConstants.PRODUCT_RECOMMEND_EXISTS;
import static cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeConstants.PRODUCT_RECOMMEND_NOT_EXISTS;

/**
 * 商品推荐 Service
 */
@Service
@Validated
public class ProductRecommendService {

    @Autowired
    private ProductRecommendMapper productRecommendMapper;

    /**
     * 获得商品推荐列表
     *
     * @param listReqDTO 列表查询 DTO
     * @return 商品推荐列表
     */
    public List<ProductRecommendRespDTO> listProductRecommends(ProductRecommendListReqDTO listReqDTO) {
        List<ProductRecommendDO> productRecommends = productRecommendMapper.selectList(listReqDTO);
        return ProductRecommendConvert.INSTANCE.convertList(productRecommends);
    }

    /**
     * 获得商品推荐分页
     *
     * @param pageReqDTO 分页查询 DTO
     * @return 商品推荐分页
     */
    public PageResult<ProductRecommendRespDTO> pageProductRecommend(ProductRecommendPageReqDTO pageReqDTO) {
        IPage<ProductRecommendDO> productRecommendPage = productRecommendMapper.selectPage(pageReqDTO);
        return ProductRecommendConvert.INSTANCE.convertPage(productRecommendPage);
    }

    /**
     * 创建商品推荐
     *
     * @param createReqDTO 商品推荐信息
     * @return 商品推荐编号
     */
    public Integer createProductRecommend(ProductRecommendCreateReqDTO createReqDTO) {
        // 校验商品是否已经推荐
        if (productRecommendMapper.selectByProductSpuIdAndType(createReqDTO.getProductSpuId(), createReqDTO.getType()) != null) {
            throw ServiceExceptionUtil.exception(PRODUCT_RECOMMEND_EXISTS);
        }
        // 保存到数据库
        ProductRecommendDO productRecommend = ProductRecommendConvert.INSTANCE.convert(createReqDTO);
        productRecommendMapper.insert(productRecommend);
        // 返回成功
        return productRecommend.getId();
    }

    public void updateProductRecommend(ProductRecommendUpdateReqDTO updateReqDTO) {
        // 校验更新的商品推荐存在
        if (productRecommendMapper.selectById(updateReqDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_RECOMMEND_NOT_EXISTS);
        }
        // 校验商品是否已经推荐
        ProductRecommendDO existProductRecommend = productRecommendMapper.selectByProductSpuIdAndType(updateReqDTO.getProductSpuId(), updateReqDTO.getType());
        if (existProductRecommend != null && !existProductRecommend.getId().equals(updateReqDTO.getId())) {
            throw ServiceExceptionUtil.exception(PRODUCT_RECOMMEND_EXISTS.getCode());
        }
        // 更新到数据库
        ProductRecommendDO updateProductRecommend = ProductRecommendConvert.INSTANCE.convert(updateReqDTO);
        productRecommendMapper.updateById(updateProductRecommend);
    }

    public void deleteProductRecommend(Integer productRecommendId) {
        // 校验更新的商品推荐存在
        if (productRecommendMapper.selectById(productRecommendId) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_RECOMMEND_NOT_EXISTS);
        }
        // 更新到数据库
        productRecommendMapper.deleteById(productRecommendId);
    }

}
