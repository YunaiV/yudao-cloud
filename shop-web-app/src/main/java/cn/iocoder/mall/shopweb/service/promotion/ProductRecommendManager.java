package cn.iocoder.mall.shopweb.service.promotion;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.spu.ProductSpuFeign;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.ProductRecommendFeign;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuRespVO;
import cn.iocoder.mall.shopweb.convert.promotion.ProductRecommendConvert;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 商品推荐 Manager
 */
@Service
@Validated
public class ProductRecommendManager {
    @Autowired
    private ProductRecommendFeign productRecommendFeign;
    @Autowired
    private ProductSpuFeign productSpuFeign;

    public Map<Integer, Collection<ProductSpuRespVO>> listProductRecommends() {
        // 查询商品推荐列表
        CommonResult<List<ProductRecommendRespDTO>> listProductRecommendsResult = productRecommendFeign.listProductRecommends(
                new ProductRecommendListReqDTO().setStatus(CommonStatusEnum.ENABLE.getValue()));
        listProductRecommendsResult.checkError();
        listProductRecommendsResult.getData().sort(Comparator.comparing(ProductRecommendRespDTO::getSort)); // 排序，按照 sort 升序
        // 获得商品集合
        Map<Integer, ProductSpuRespDTO> spuMap = this.getProductSkuMap(listProductRecommendsResult.getData());
        // 组合结果，返回
        Multimap<Integer, ProductSpuRespVO> result = HashMultimap.create();
        listProductRecommendsResult.getData().forEach(productRecommendBO -> result.put(productRecommendBO.getType(),
                ProductRecommendConvert.INSTANCE.convert(spuMap.get(productRecommendBO.getProductSpuId()))));
        return result.asMap();
    }

    private Map<Integer, ProductSpuRespDTO> getProductSkuMap(List<ProductRecommendRespDTO> productRecommends) {
        CommonResult<List<ProductSpuRespDTO>> listProductSpusResult = productSpuFeign.listProductSpus(
                CollectionUtils.convertSet(productRecommends, ProductRecommendRespDTO::getProductSpuId));
        listProductSpusResult.checkError();
        return CollectionUtils.convertMap(listProductSpusResult.getData(), ProductSpuRespDTO::getId);
    }

}
