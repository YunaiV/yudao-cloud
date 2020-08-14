package cn.iocoder.mall.productservice.manager.sku;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.mall.productservice.convert.sku.ProductSkuConvert;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.productservice.service.sku.ProductSkuService;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 商品 SKU Manager
 */
@Service
public class ProductSkuManager {

    @Autowired
    private ProductSkuService productSkuService;

    /**
     * 获得商品 SKU
     *
     * @param productSkuId 商品 SKU编号
     * @return 商品 SKU
     */
    public ProductSkuRespDTO getProductSku(Integer productSkuId) {
        ProductSkuBO productSkuBO = productSkuService.getProductSku(productSkuId);
        return ProductSkuConvert.INSTANCE.convert(productSkuBO);
    }

    /**
     * 获得商品 SKU 列表
     *
     * @param queryReqDTO 商品 SKU 列表的查询请求 DTO
     * @return 商品 SKU列表
     */
    public List<ProductSkuRespDTO> listProductSkus(ProductSkuListQueryReqDTO queryReqDTO) {
        // 获得商品 SKU 列表
        List<ProductSkuBO> productSkuBOs = productSkuService.listProductSkus(
                ProductSkuConvert.INSTANCE.convert(queryReqDTO));
        if (CollectionUtils.isEmpty(productSkuBOs)) {
            return Collections.emptyList();
        }
        //
        return ProductSkuConvert.INSTANCE.convertList03(productSkuBOs);
    }

}
